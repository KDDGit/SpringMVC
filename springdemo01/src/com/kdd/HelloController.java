package com.kdd;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("sayHello方法执行了。。。");
		return "success";
	}
}
