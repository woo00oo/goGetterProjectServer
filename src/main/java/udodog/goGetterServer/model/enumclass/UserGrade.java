package udodog.goGetterServer.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserGrade {

    USER("일반회원"),
    ADMIN("관리자")
    ;

    private String description;
}
