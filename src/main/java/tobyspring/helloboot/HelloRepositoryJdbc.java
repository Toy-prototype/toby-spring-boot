package tobyspring.helloboot;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {
    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM HELLO WHERE NAME = '" + name + "'",
                    (rs, rowNum) -> new Hello(rs.getString("name"), rs.getInt("count"))
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if(hello == null)
            jdbcTemplate.update("INSERT INTO HELLO VALUES(?, ?)", name, 1);
         else
            jdbcTemplate.update("UPDATE HELLO SET COUNT = ? WHERE NAME = ?", hello.getCount()+1 , name);
    }
}
