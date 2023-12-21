package tobyspring.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tobyspring.config.autoconfig.DispatcherServletConfig;
import tobyspring.config.autoconfig.TomcatServletWebServerFactoryConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // TYPE: class, interface, enum, annotation
@Configuration
@ComponentScan
@Import({DispatcherServletConfig.class, TomcatServletWebServerFactoryConfig.class})
public @interface MySpringBootApplication {
}
