package tobyspring.study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    @Test
    public void conf() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        assertThat(bean1.common).isSameAs(bean2.common);
    }

    @DisplayName("프록시를 이용한다면 Configuration 어노테이션 처럼 동작하게 만든다.")
    @Test
    public void confProxy() {
        MyConfigProxy proxy = new MyConfigProxy();

        Bean1 bean1 = proxy.bean1();
        Bean2 bean2 = proxy.bean2();

        assertThat(bean1.common).isSameAs(bean2.common);
    }


    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        public Common common() {
            if (this.common == null)
                this.common = super.common();
            return this.common;
        }

    }


    @Configuration
    static class MyConfig {
        @Bean
        public Common common() {
            return new Common();
        }

        @Bean
        public Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Common {
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }
    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }
}