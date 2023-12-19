package tobyspring.helloboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.refresh(); //구성 정보를 이용해 컨테이너 초기화 작업 수행

		ServletWebServerFactory factory = new TomcatServletWebServerFactory();
		WebServer webServer = factory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					//인증, 보안, 다국어, 공통 등의 작업
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals("GET")) {
						String name = req.getParameter("name");

						HelloController HelloControllerBean = applicationContext.getBean(HelloController.class);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(HelloControllerBean.hello(name));
					} else if (req.getRequestURI().equals("/user")) {
						// ...
					} else {
						resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}
				}
			}).addMapping("/*");
			}
		);
		webServer.start();
	}
}
