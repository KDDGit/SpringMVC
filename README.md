# SpringMVC
### SpringMVC案例和笔记

#### Spring mvc概述
	Spring mvc是基于java实现的mvc设计模型的请求驱动类型的轻量级web框架。Spring框架提供了构建web应用程序的全功能mvc模块。    
	它通过一套注解，让一个简单的java类成为处理请求的控制器，而无需实现任何接口。同时它还支持RESTFull编程风格的请求。    
  
#### 三层架构和mvc模型
 	JavaEE中的三层架构：
	表现层 		web层		mvc它只是表现层的一种设计模式
	业务层		service层
	持久层		dao层	
	
	mvc模型
		Model（模型）
			指我们的数据类型，用于封装数据。如：实体类，数组等
		View（视图）
			分动态资源和静态资源。jsp，html，js，servlet
		Controller（控制器）
			是应用程序中处理用户交互部分，就是处理程序逻辑的。
			先前用servlet，后升级为filter，现在升级为Java类
#### Spring mvc入门案例(springmvc01)
	1、eclipse中新建一个Dynamic web Project项目	
		Dynamic web module version 这项什么意思？选2.5
	2、导入jar包
		ioc的五个jar包
			commons-logging
			spring-beans
			spring-context
			spring-core
			spring-expression
		spring-mvc
			spring-web 
			spring-webmvc
		注解包
			aop的核心包
	
	3、类路径下创建Springmvc.xml
	4、导约束
		<?xml version="1.0" encoding="UTF-8"?>
		<beans 	xmlns="http://www.springframework.org/schema/beans"
		xmlns:mvc="http://www.springframework.org/schema/mvc"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans.xsd
							http://www.springframework.org/schema/mvc
							http://www.springframework.org/schema/mvc/spring-mvc.xsd
							http://www.springframework.org/schema/context
							http://www.springframework.org/schema/context/spring-context.xsd">
	5、在web.xml配置核心控制器
		注：快捷键：ctrl+shift+t
		<!-- 配置spring的核心控制器 -->
		<servlet>
			<servlet-name>dispatcherServlet<servlet-name>
			<servlet-class>org.springframework.web.servlet.DidpatcherServlet</servlet-class>
			<!-- 配置servlet的初始化参数，告知servlet springmvc的配置文件位置 -->
			<init-param>
				<param-name>contextConfigLocation</param-name>  //特定名字
				<param-value>classpath:springmvc.xml</param-value>
			</init-param>
			<!-- 配置servlet启动顺序 -->
			<load-on-startup>1</load-on-startup>  //非零正整数
		</servlet>
		<servlet-mapping>
			<servlet-name>dispatcherServlet</servlet-name>
			<url-pattern>/<url-pattern> //资源全拦截
		</servlet-mapping>
	6、在webcontent下创建index.jsp文件
		修改编码，title，<a href="${pageContext.request.contextPath}/hello">第一个springmvc程序</a>
	
	7、创建一个类HelloController
		public class helloController{
			public Sting sayHello(){
				System.out.println("HelloController的sayHello方法执行了。。。");
				return "success";
			}
		}
	8、在WEB-INF下创建pages/success.jsp
		
	9、在类上添加注解：
		@Controller("helloController")
	
	10、在springmvc中要配置
		<!-- 告知Spring创建容器时要扫描的包 -->
		<context:component-scan base-package="com.kdd.controller"></context:component-scan>
	
	11、在方法上面添加注解：
		@RequestMapping("/hello")//请求的映射：它要和浏览器请求url保持一致
	
	12、在springmvc中配置视图解析器
		注：快捷键：ctrl+shift+t
		<!-- 配置springmvc的视图解析器 -->
		<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/WEB-INF/pages/"></property>
			<property name="suffix" value=".jsp"></property>
		</bean>
	
	13、部署到tomcat中，启动
	
	14、在页面中访问：localhost：8080/项目名
	
#### 入门案例执行过程		
	可分为容器启动过程、浏览器请求过程。如图    
      	![image](https://github.com/KDDGit/SpringMVC/blob/master/Image/%E6%A1%88%E4%BE%8B%E6%89%A7%E8%A1%8C%E8%BF%87%E7%A8%8B%E5%9B%BE.jpg)			DispatcherServlet的执行过程
	看DispatcherServlet的源码，主要方法，service，doService，doDispatch，mv=ha.handle();	  
	
#### 入门案例中涉及的组件
	
	DispatcherServlet：前段控制器
		用户请求到达前段控制器，它就相当于mvc模式中的c，dispatcherServlet是整个流程控制的中心，
		由它调用其他组件处理用户的请求，dispatcherServlet的存在降低了组件之间的耦合性。
	HandlerMapping：处理器映射器
		HandlerMapping负责根据用户请求找到Handler即处理器，SpringMVC提供了不同的映射器实现不同
		映射方式，例如：配置文件方式，实现接口方式，注解方式等。
	Handler：处理器
		它就是我们开发中要编写的具体业务控制器。由DispatcherServlet把用户请求转发到Handler。由
		Handler对具体的用户请求进行处理。
	HandlAdapter：处理器适配器
		通过HandlAdapter对处理器进行执行，这是适配器模式的应用，通过扩展适配器可以对更多类型的处
		理器进行执行
	View Resolver：视图解析器
		View Resolver负责将处理结果生成View视图，View Resolver首先根据逻辑视图名解析成物理视图名，
		即具体的页面地址，再生成View视图对象，最后对View进行渲染将处理结果通过页面展示给用户。
	View：视图
		SpringMVC框架提供了很多的View视图类型的支持，包括：jstlView、freemarkerView、pdfView等。我们
		最常用的视图就是jsp
		一般情况下需要通过页面标签或页面模板技术将模型数据通过页面展示给用户，需要由程序员根据业务需求开发具体
		的页面。
			
			
			
