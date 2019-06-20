package com.kdd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("helloController")
public class HelloController {

	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("HelloController中的sayHello方法执行了");
		return "success";
	}
}
