package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        jdbcTemplate.execute("create table if not exists hello(id varchar(10) primary key, name varchar(20))");
    }

    @Test
    public void test() {
        jdbcTemplate.execute("insert into hello values('1', 'Spring')");
        jdbcTemplate.execute("insert into hello values('2', 'jaeWoo')");

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);

        Assertions.assertThat(count).isEqualTo(2);
    }
}
