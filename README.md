# SpringMVC
### SpringMVC案例和笔记

#### Spring mvc概述
	Spring mvc是基于java实现的mvc设计模型的请求驱动类型的轻量级web框架。Spring框架提供了构建web应用程序的全功能mvc模块。    
	它通过一套注解，让一个简单的java类成为处理请求的控制器，而无需实现任何接口。同时它还支持RESTFull编程风格的请求。    
  
#### 三层架构和mvc模型
 JavaEE中的三层架构：
	表现层 		web层			mvc它只是表现层的一种设计模式
	业务层		 service层
	持久层		 dao层	
	
	mvc模型
		Model（模型）
			指我们的数据类型，用于封装数据。如：实体类，数组等
		View（视图）
			分动态资源和静态资源。jsp，html，js，servlet
		Controller（控制器）
			是应用程序中处理用户交互部分，就是处理程序逻辑的。
			先前用servlet，后升级为filter，现在升级为Java类
