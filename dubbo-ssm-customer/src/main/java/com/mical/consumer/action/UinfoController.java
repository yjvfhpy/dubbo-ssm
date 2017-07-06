package com.mical.consumer.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mical.api.IUinfoService;
import com.mical.consumer.entity.Goods;
import com.mical.entity.Uinfo;

@Controller
@RequestMapping(value = "/uinfo")
public class UinfoController {
	@Resource
	private IUinfoService uinfoService;

	@RequestMapping(value = "/list")
	public String list(Map<String, Object> map) {
		// uinfoService.saysay("teststtttt");
		List<Uinfo> list = uinfoService.sel();
		// printClassDefinition(uinfoService.getClass());

		map.put("list", list);
		return "/uinfo/list";
	}

	@RequestMapping(value = "buy")
	@ResponseBody
	public String buy(@Valid Goods goods, BindingResult result) {
		
		// aspect代替参数校验
		if (result.hasErrors()) {
			String errorInfo = "[" + result.getFieldError().getField() + "]" + result.getFieldError().getDefaultMessage();
			return errorInfo;
		}
		
		int a = 3;
    	int b = 0;
    	System.out.println(a/b);
		

		return "success";
	}

	public static String getModifier(int modifier) {
		String result = "";
		switch (modifier) {
		case Modifier.PRIVATE:
			result = "private";
		case Modifier.PUBLIC:
			result = "public";
		case Modifier.PROTECTED:
			result = "protected";
		case Modifier.ABSTRACT:
			result = "abstract";
		case Modifier.FINAL:
			result = "final";
		case Modifier.NATIVE:
			result = "native";
		case Modifier.STATIC:
			result = "static";
		case Modifier.SYNCHRONIZED:
			result = "synchronized";
		case Modifier.STRICT:
			result = "strict";
		case Modifier.TRANSIENT:
			result = "transient";
		case Modifier.VOLATILE:
			result = "volatile";
		case Modifier.INTERFACE:
			result = "interface";
		}
		return result;
	}

	public static void printClassDefinition(Class<?> clz) {

		String clzModifier = getModifier(clz.getModifiers());
		if (clzModifier != null && !clzModifier.equals("")) {
			clzModifier = clzModifier + " ";
		}
		String superClz = clz.getSuperclass().getName();
		if (superClz != null && !superClz.equals("")) {
			superClz = "extends " + superClz;
		}

		Class<?>[] interfaces = clz.getInterfaces();

		String inters = "";
		for (int i = 0; i < interfaces.length; i++) {
			if (i == 0) {
				inters += "implements ";
			}
			inters += interfaces[i].getName();
		}

		System.out.println(clzModifier + clz.getName() + " " + superClz + " " + inters);
		System.out.println("{");

		Field[] fields = clz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String modifier = getModifier(fields[i].getModifiers());
			if (modifier != null && !modifier.equals("")) {
				modifier = modifier + " ";
			}
			String fieldName = fields[i].getName();
			String fieldType = fields[i].getType().getName();
			System.out.println("    " + modifier + fieldType + " " + fieldName + ";");
		}

		System.out.println();

		Method[] methods = clz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			String modifier = getModifier(method.getModifiers());
			if (modifier != null && !modifier.equals("")) {
				modifier = modifier + " ";
			}

			String methodName = method.getName();

			Class<?> returnClz = method.getReturnType();
			String retrunType = returnClz.getName();

			Class<?>[] clzs = method.getParameterTypes();
			String paraList = "(";
			for (int j = 0; j < clzs.length; j++) {
				paraList += clzs[j].getName();
				if (j != clzs.length - 1) {
					paraList += ", ";
				}
			}
			paraList += ")";

			clzs = method.getExceptionTypes();
			String exceptions = "";
			for (int j = 0; j < clzs.length; j++) {
				if (j == 0) {
					exceptions += "throws ";
				}

				exceptions += clzs[j].getName();

				if (j != clzs.length - 1) {
					exceptions += ", ";
				}
			}

			exceptions += ";";

			String methodPrototype = modifier + retrunType + " " + methodName + paraList + exceptions;

			System.out.println("    " + methodPrototype);

		}
		System.out.println("}");
	}

}
