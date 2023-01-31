package org.example.dao;

import org.example.controllers.PersonController;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Component
@RequestMapping("/person")
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("Select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("Select  * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("Insert into person(full_name,year_of_birth) values (?,?)"
                , person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("Update person set full_name=?,year_of_birth=? where id=?"
                , person.getFullName(), person.getYearOfBirth(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("Delete from person where id=?", id);
    }

    //For validation of Not repeating full name
    public Optional<Person>getPersonByFullName(String fullName){
        return jdbcTemplate.query("Select * from person where full_Name=?",new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("Select * from book where person_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
