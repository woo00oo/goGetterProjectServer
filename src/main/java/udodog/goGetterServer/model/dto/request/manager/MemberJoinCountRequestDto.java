package udodog.goGetterServer.model.dto.request.manager;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberJoinCountRequestDto {
    @NotEmpty Integer year;        // 조회 년도
    @NotEmpty Integer month;       // 조회 월
} // Class 끝
