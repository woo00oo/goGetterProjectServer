package udodog.goGetterServer.model.entity.enumclass;

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
