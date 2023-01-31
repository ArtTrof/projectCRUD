package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("Select * from book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("Select * from book where id=?",new Object[]{id}
        ,new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book){
         jdbcTemplate.update("INSERT INTO Book(book_name,author,year) values(?,?,?)",
                book.getBookName(),book.getAuthor(),book.getYear());
    }

    public void update(int id,Book book){
        jdbcTemplate.update("Update book set book_name =?,author=?,year=? where id=?",
                book.getBookName(),book.getAuthor(),book.getYear(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("Delete  from book where id=?",id);
    }

    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("Select Person.* from Book join Person on book.person_id=Person.id " +
                "where book.id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }
    public void assign(int id,Person person){
        jdbcTemplate.update("Update book set person_id=? where id=?",person.getId(),id);
    }
}
