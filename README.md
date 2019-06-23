# SpringMVC案例和笔记
## Spring mvc概述
Spring mvc是基于java实现的**mvc**设计模型的请求驱动类型的轻量级web框架。**Spring框架提供了构建web应用程序的全功能mvc模块**。
它通过一套注解，让一个**简单的java类成为处理请求的控制器**，而无需实现任何接口。同时它还支持**RESTFul编程风格**的请求。
## 三层架构和mvc模型
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
## Spring mvc入门案例(springmvc01)
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
	
## 入门案例执行过程		
	可分为容器启动过程、浏览器请求过程。如图    
      	![image](https://github.com/KDDGit/SpringMVC/blob/master/Image/%E6%A1%88%E4%BE%8B%E6%89%A7%E8%A1%8C%E8%BF%87%E7%A8%8B%E5%9B%BE.jpg)			DispatcherServlet的执行过程
	看DispatcherServlet的源码，主要方法，service，doService，doDispatch，mv=ha.handle();	  
	
## 入门案例中涉及的组件
	
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
		
## annotation-driven标签
	Spring mvc中的三大组件：处理器映射器、处理器适配器、视图解析器
	使用<mvc:annotation-driven>自动加载RequestMappingHanlerMapping（处理器映射器）和 RequestMappingHandlerAdapter（处理适配器）。可用在Springmvc.xml配置文件中使用<mvc:annotation-driven>替代注解处理器和适配器的配置。如：
	<!-- 配置Spring mvc的注解驱动 -->
	<mvc:annotation-driven></mvc:annotation-driven>			

## RequestMapping注解(springmvc02)
	看下源码
		能使用于什么类型？？？？具体查看源码
		查看源码，ctrl+左键点击@RequestMapping即可
	作用：
		建立请求url和控制器方法的对应关系  
	出现位置：
		写在方法：
			和请求url进行绑定   
		写在类上：
			给访问url进行窄化处理   
			可以让我们的url变得模块化的形成  
	属性：
		value，用于访问指定访问url和执行方法
		method，用于指定请求的方式
		params，用于指定限制请求参数的条件。支持简单表达式。要求key和value完全一样
		headers，用于指定限制请求消息头的条件。
		注：
			以上四个属性只要出现2个或以上时，他们的关系是与的关系(都成立)。
	
		示例：
			/**jsp页面**/
			<!-- 第一个测试method="get" -->
			<a href="${pageContext.request.contextPath}/test/helloGet">@RequestMapping的method方法get请求</a>
			<!-- 测试method="post"请求 -->
			<form action="test/helloPost" method="post">
				<input type="submit" value="method=post的请求">
			</form>
			<hr/>
			<!-- 测试params的属性 -->
			<a href="test/helloParams?money=1001">测试RequestMapping的params属性</a>
		-------------------------------------------------------------------------------------------------
			/**Springmvc.xml**/
			<!-- 配置springmvc要扫描的包 -->
			<context:component-scan base-package="com.kdd.springmvc02"></context:component-scan>
			<!-- 配置视图解析器 -->
			<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<property name="prefix" value="/WEB-INF/page/"></property>
				<property name="suffix" value=".jsp"></property>
			</bean>
		-------------------------------------------------------------------------------------------------	
			/**控制器**/
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
		注：拷贝项目，项目右键-》properties-》Web Project Settings-》Context root：
		    部署到tomcat显示原来名字后缀：
		    打开Navigator视图-》打开项目.settings目录-》org.eclipse.wst.common.component-》
		    修改web-module、name="java-out-path"的值
		    访问控制器的小细节，一个长的url地址，一个短的url地址。短的url前不要加/，会访问根目录

## 请求参数绑定
	支持绑定的数据类型：
			基本类型参数：
				包括基本类型和String类型
			POJO类型参数：
				包括实体类，以及关联的实体类
			数组和集合类型参数：
				包括List结构和Map结构的集合（包括数组）
			注：
				SpringMVC 绑定请求参数是自动绑定的，但是要想使用，必须遵循使用要求。
	使用要求：
		如果是节本类型或者String类型：
			要求我们的参数名称必须和控制器中方法的形参名称保持一致。（严格区分大小写）
		如果是POJO类型，或者它的关联对象：
			要求表单中参数名称和POJO类的属性名称保持一致。并且控制器方法的参数类是是POJO类型
		如果是集合类型，有两种方式：
	
	简单示例：
		/** 简单类型 **/
		<!-- 测试参数绑定-Integer -->
		<a href="test/helloGetInteger?money=100">测试参数绑定Integer</a>
		<hr/>
		<!-- 测试参数绑定-String -->
		<a href="test/helloGetString?name=小明">测试参数绑定String</a>
		
		@Controller
		@RequestMapping("/test")
		public class HelloController {
			
			@RequestMapping("/helloGetInteger")
			public String methodGet(Integer money) {
				System.out.println("测试参数绑定Integer，简单案例执行了。。+"+money);
				return "success";
			}
			
			@RequestMapping("/helloGetString")
			public String methodString(String name) {
				System.out.println("测试参数绑定String，简单案例执行了。。+"+name);
				return "success";
			}
			
		}
	---------------------------------------------------------------------------------------			
		/** pojo类型 **/
		<!-- 测试参数绑定-pojo -->
		<form action="test/helloGetPojo" method="post">
			姓名：<input type="text" name="name"><br/>
			年龄：<input type="text" name="age"><br/>
			<input type="submit" value="测试参数绑定Pojo"/>
		</form>
		<hr/>
		<!-- 测试参数绑定-pojo及关联类 -->
		<form action="test/helloGetPojo" method="post">
			姓名：<input type="text" name="name"><br/>
			年龄：<input type="text" name="age"><br/>
			城市：<input type="text" name="adress.cityName"><br/>
			区域：<input type="text" name="adress.areaName"><br/>
			<input type="submit" value="测试参数绑定Pojo及关联类"/>
		</form>
		
		@RequestMapping("/helloGetPojo")
		public String methodPojo(People people) {
			System.out.println("测试参数绑定Pojo，简单案例执行了。。+"+people);
			return "success";
		}
		
		public class People implements Serializable {
			private String name;
			private Integer age;
			private Adress adress;
			。。。。。。。
		}
		public class Adress implements Serializable{
			private String cityName;
			private String areaName;
			。。。。。。
		}
	---------------------------------------------------------------------------------------			
	注：
		psot请求乱码问题？
			1、配置web.xml中的过滤器
			<!-- 配置springmvc编码过滤器 -->
			<filter>
				<filter-name>characterEncodingFilter</filter-name>
				<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
				<!-- 设置过滤器中的属性值 -->
				<init-param>
					<param-name>encoding</param-name>
					<param-value>UTF-8</param-value>
				</init-param>
			</filter>
			
			<filter-mapping>
				<filter-name>characterEncodingFilter</filter-name>
				<url-pattern>/*</url-pattern>
			</filter-mapping>
			2、配置springmvc.xml中的不需要拦截的文件
			
			<mvc:annotation-driven></mvc:annotation-driven>	
			<!-- 设置静态资源不过滤 -->
			<mvc:resources location="/css/" mapping="/css/**"></mvc:resources>	
			<mvc:resources location="/images/" mapping="/images/**"></mvc:resources>	
			<mvc:resources location="/scripts/" mapping="/javascript/**"></mvc:resources>
	
	
		get方式乱码解决：
			tomcat就给解决了。假如get方式乱码，按照下面方式配置
			tomcat对get和post请求处理方式不同，get请求的编码问题，要改tomcat的server.xml配置文件，如下：
			<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
			改为：
			<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" useBodyEncodingForURI="true"/>
			如果遇到ajax请求仍然乱码，请把： useBodyEncodingForURI="true"改为URIEncoding="UTF-8"即可。
			
## pojo类型中集合类型封装：
	示例：
	<!-- 测试参数绑定-集合类型list -->
	<form action="test/helloList" method="post">
		用户密码：<input type="text" name="username" /><br/>
		用户密码：<input type="password" name="password" /><br/>
		用户年龄：<input type="text" name="age" /><br/>
		账户1名称：<input type="text" name="accountList[0].name" /><br/>
		账户1金额：<input type="text" name="accountList[0].money" /><br/>
		账户2名称：<input type="text" name="accountList[1].name" /><br/>
		账户2金额：<input type="text" name="accountList[1].money" /><br/>
		<input type="submit" value="测试参数绑定集合list"/>
	</form>
	<!-- 测试参数绑定-集合类型Map -->
	<form action="test/helloList" method="post">
		用户密码：<input type="text" name="username" /><br/>
		用户密码：<input type="password" name="password" /><br/>
		用户年龄：<input type="text" name="age" /><br/>
		账户1名称：<input type="text" name="accountMap['one'].name" /><br/>
		账户1金额：<input type="text" name="accountMap['one'].money" /><br/>
		账户2名称：<input type="text" name="accountMap['two'].name" /><br/>
		账户2金额：<input type="text" name="accountMap['two'].money" /><br/>
		<input type="submit" value="测试参数绑定结合Map"/>
	</form>
	-----------------------------------------------------------------------------
	@RequestMapping("/helloList")
	public String methodList(User user) {
		System.out.println("测试参数绑定Pojo，简单案例执行了。。+"+user);
		return "success";
	}
	---------------------------------------------------------------------------------
	public class User implements Serializable {
		private String username;
		private String password;
		private Integer age;
		private List<Account> accountList;
		private Map<String,Account> accountMap;
		。。。。。
	}
	public class Account implements Serializable {
		private String name;
		private Integer money;
		。。。。。。。。
	}
			
	
