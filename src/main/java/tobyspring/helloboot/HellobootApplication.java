package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class HellobootApplication {


	public static void main(String[] args) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory factory = new TomcatServletWebServerFactory();
				WebServer webServer = factory.getWebServer(servletContext ->
						servletContext.addServlet(
								"dispatcherServlet",
								new DispatcherServlet(this)
						).addMapping("/*")
				);
				webServer.start();
			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh(); //구성 정보를 이용해 컨테이너 초기화 작업 수행
	}
}
