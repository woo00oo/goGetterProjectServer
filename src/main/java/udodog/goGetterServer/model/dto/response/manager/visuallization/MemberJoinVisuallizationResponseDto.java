package udodog.goGetterServer.model.dto.response.manager.visuallization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinVisuallizationResponseDto {

    Long month;

    public MemberJoinVisuallizationResponseDto(MemberJoinVisuallizationResponseDto memberJoinVisuallizationResponseDto) {

        this.month = memberJoinVisuallizationResponseDto.getMonth();

    } // 생성자 끝
}  // Class 끝
