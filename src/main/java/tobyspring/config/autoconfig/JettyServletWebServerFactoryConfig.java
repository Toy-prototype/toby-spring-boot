package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.ConditionalOnMyClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalOnMyClass("org.eclipse.jetty.server.Server")
public class JettyServletWebServerFactoryConfig {
    @Bean("jettyServletWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
