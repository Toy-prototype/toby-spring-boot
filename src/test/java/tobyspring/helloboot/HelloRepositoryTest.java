package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class HelloRepositoryTest {
    @Autowired HelloRepository helloRepository;
    @Autowired JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(20) primary key, count int)");
    }

    @Test
    void findHello(){
        Assertions.assertThat(helloRepository.findHello("jaeWoo")).isNull();
    }

    @Test
    void increaseCount(){
        Assertions.assertThat(helloRepository.countOf("jaeWoo")).isEqualTo(0);

        helloRepository.increaseCount("jaeWoo");
        Assertions.assertThat(helloRepository.countOf("jaeWoo")).isEqualTo(1);
    }
}
