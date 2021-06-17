package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String author;

    private String genre;

    private Integer price;

    @Builder
    public Book(String bookName, String author, String genre, Integer price) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }
}
