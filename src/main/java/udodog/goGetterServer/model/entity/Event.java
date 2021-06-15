package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate start_date;

    private LocalDate end_date;

    private String imgUrl;

    @Builder
    public Event(Long id, String title, String content, LocalDate start_date, LocalDate end_date, String imgUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.imgUrl = imgUrl;
    }
}
