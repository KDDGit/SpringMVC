package com.kdd.springmvc02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class HelloController {
	@RequestMapping("/helloGet")
	public String methodGet() {
		System.out.println("测试@RequestMapping的method方法的get请求执行了。。");
		return "success";
	}
	
	@RequestMapping(value="/helloPost",method=RequestMethod.POST)
	public String methodPost() {
		System.out.println("测试@RequestMapping的method方法的post请求执行了。。");
		return "success";
	}
	
	@RequestMapping(value="/helloParams",params="money=1001")
	public String paramsGet() {
		System.out.println("测试@RequestMapping的params的属性求执行了。。");
		return "success";
	}
}
