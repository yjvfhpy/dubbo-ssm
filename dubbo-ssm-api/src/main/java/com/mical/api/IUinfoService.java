package com.mical.api;

import java.util.List;
import com.mical.entity.Uinfo;;

public interface IUinfoService {
	// 查询
	List<Uinfo> sel();
	
	String saysay(String str);

}
