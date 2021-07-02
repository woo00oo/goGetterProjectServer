package udodog.goGetterServer.model.dto.response.event;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProgressEventsResponseDto {

    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    public ProgressEventsResponseDto(Long id, String title, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
