package udodog.goGetterServer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import udodog.goGetterServer.model.dto.request.event.EventUpdateRequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    private String imgUrl;

    private Long couponBoxId;

    @Builder
    public Event(String title, String content, LocalDate startDate, LocalDate endDate, String imgUrl, Long couponBoxId) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
        this.couponBoxId = couponBoxId;
    }

    public void update(EventUpdateRequestDto request){
        this.title = request.getTitle();
        this.content = request.getContent();
        this.imgUrl = request.getImgUrl();
        this.couponBoxId = request.getCouponBoxId();
    }
}
