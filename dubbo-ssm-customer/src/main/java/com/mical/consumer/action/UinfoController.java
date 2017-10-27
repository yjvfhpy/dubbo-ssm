package com.mical.consumer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.mical.api.ICacheService;
import com.mical.api.IUinfoService;
import com.mical.api.IValidationService;
import com.mical.api.validation.ValidationParameter;
import com.mical.consumer.entity.Goods;
import com.mical.entity.Uinfo;

@Controller
@RequestMapping(value = "/uinfo")
public class UinfoController extends BaseController {
	@Resource
	private IUinfoService uinfoService;

	@Resource
	private IValidationService validationService;

	@Resource
	private ICacheService cacheService;

	@RequestMapping(value = "/list")
	public String list(Map<String, Object> map) {
		List<Uinfo> list = uinfoService.sel();
		map.put("list", list);
		return "/uinfo/list";
	}
	
	
	
	

	@RequestMapping(value = "/print")
	@ResponseBody
	public String print(HttpServletRequest request) {
		printClassDefinition(uinfoService.getClass());
		return "success";
	}
	
	
	
	

	@RequestMapping(value = "buy")
	@ResponseBody
	public String buy(@Valid Goods goods, BindingResult result) {

		// aspect代替参数校验
		if (result.hasErrors()) {
			String errorInfo = "[" + result.getFieldError().getField() + "]"
					+ result.getFieldError().getDefaultMessage();
			return errorInfo;
		}

		int a = 3;
		int b = 0;
		System.out.println(a / b);
		return "success";
	}

	
	
	
	@RequestMapping(value = "/validation")
	@ResponseBody
	public String validation(HttpServletRequest request) {

		// Save OK
		ValidationParameter parameter = new ValidationParameter();
		parameter.setName("liangfei");
		parameter.setEmail("liangfei@liang.fei");
		parameter.setAge(50);
		parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
		parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
		validationService.save(parameter);
		System.out.println("Validation Save OK");

		// Save Error
		try {
			parameter = new ValidationParameter();
			validationService.save(parameter);
			System.err.println("Validation Save ERROR");
		} catch (RpcException e) {
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
			System.out.println(violations);
		}

		// Delete OK
		validationService.delete(2, "abc");
		System.out.println("Validation Delete OK");

		// Delete Error
		try {
			validationService.delete(0, "abc");
			System.err.println("Validation Delete ERROR");
		} catch (RpcException e) {
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
			System.out.println(violations);
		}

		return "success";
	}

	
	
	
	@RequestMapping(value = "/cache")
	@ResponseBody
	public String cache(HttpServletRequest request) throws Exception {
		// 测试缓存生效，多次调用返回同样的结果。(服务器端自增长返回值)
		String fix = null;
		for (int i = 0; i < 5; i++) {
			String result = cacheService.findCache("0");
			if (fix == null || fix.equals(result)) {
				System.out.println("OK: " + result);
			} else {
				System.err.println("ERROR: " + result);
			}
			fix = result;
			Thread.sleep(500);
		}

		// LRU的缺省cache.size为1000，执行1001次，应有溢出
		for (int n = 0; n < 1001; n++) {
			String pre = null;
			for (int i = 0; i < 10; i++) {
				String result = cacheService.findCache(String.valueOf(n));
				if (pre != null && !pre.equals(result)) {
					System.err.println("ERROR: " + result);
				}
				pre = result;
			}
		}

		// 测试LRU有移除最开始的一个缓存项
		String result = cacheService.findCache("0");
		if (fix != null && !fix.equals(result)) {
			System.out.println("OK: " + result);
		} else {
			System.err.println("ERROR: " + result);
		}

		return "success";
	}

	/**
	 * 回声测试用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。 所有服务自动实现 EchoService
	 * 接口，只需将任意服务引用强制转型为 EchoService，即可使用。
	 * 
	 * @return
	 */
	@RequestMapping(value = "/echo")
	@ResponseBody
	public String echo(HttpServletRequest request) {
		EchoService echoService = (EchoService) uinfoService; // 强制转型为EchoService
		// 回声测试可用性
		String status = (String) echoService.$echo("OK");
		return status;
	}

	/**
	 * 上下文中存放的是当前调用过程中所需的环境信息。
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rpccontext")
	@ResponseBody
	public String rpccontext(HttpServletRequest request) {
		//// 远程调用
		String say = uinfoService.saysay("RPC CONTEXT");
		
		
		// 本端是否为消费端，这里会返回true
		boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
		// 获取最后一次调用的提供方IP地址
		String serverIP = RpcContext.getContext().getRemoteHost();
		// 获取当前服务配置信息，所有配置信息都将转换为URL的参数
		String application = RpcContext.getContext().getUrl().getParameter("application");
		
		String result = "SAYSAY:" + say + ";isConsumerSide:" + isConsumerSide + ";serverIP:" + serverIP + ";application:" + application;
		return result;
	}

}
