package tobyspring.helloboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		ServletWebServerFactory factory = new TomcatServletWebServerFactory();
		WebServer webServer = factory.getWebServer(servletContext -> {
			HelloController helloController = new HelloController();
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals("GET")) {
						String name = req.getParameter("name");
						helloController.hello(name);

						resp.setStatus(HttpServletResponse.SC_OK);
						resp.setHeader("Content-type", "text/plain");
						resp.getWriter().println("Hello " + name);
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
