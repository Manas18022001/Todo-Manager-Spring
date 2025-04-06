package com.growth.springboot.ToDoList_webapp.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //This annotation is used to tell spring that @Controller is a spring bean
public class SayHelloController {
		
	@RequestMapping("say-hello-html") //This annotation is used to tell spring that this is used to map HTTP requests to handler methods of MVC and REST controllers.
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>My first HTML page</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html body");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	//say-hello-jsp => sayhello.jsp
	//src/main/resources/META-INF/resources/WEB-INF/jsp/sayhello.jsp
	//@ResponseBody
	@RequestMapping("say-hello-jsp") //This annotation is used to tell spring that this is used to map HTTP requests to handler methods of MVC and REST controllers.
	public String sayHelloJsp() {
		return "sayhello";
	}
}
//Hard coded scripts makes our code difficult to scale, that's why we use views