package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.ConditionalOnMyClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalOnMyClass("org.apache.catalina.startup.Tomcat")
public class TomcatServletWebServerFactoryConfig {
    @Bean("tomcatServletWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

}
