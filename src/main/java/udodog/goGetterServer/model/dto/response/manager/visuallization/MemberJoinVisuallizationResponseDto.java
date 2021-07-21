package udodog.goGetterServer.model.dto.response.manager.visuallization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinVisuallizationResponseDto {

    Long January;
    Long February;
    Long March;
    Long April;
    Long May;
    Long June;
    Long July;
    Long August;
    Long September;
    Long October;
    Long November;
    Long December;

    public MemberJoinVisuallizationResponseDto(MemberJoinVisuallizationResponseDto memberJoinVisuallizationResponseDto) {

        this.January = memberJoinVisuallizationResponseDto.getJanuary();
        this.February = memberJoinVisuallizationResponseDto.getFebruary();
        this.March = memberJoinVisuallizationResponseDto.getMarch();
        this.April = memberJoinVisuallizationResponseDto.getApril();
        this.May = memberJoinVisuallizationResponseDto.getMay();
        this.June = memberJoinVisuallizationResponseDto.getJune();
        this.July = memberJoinVisuallizationResponseDto.getJuly();
        this.August = memberJoinVisuallizationResponseDto.getAugust();
        this.September = memberJoinVisuallizationResponseDto.getSeptember();
        this.October = memberJoinVisuallizationResponseDto.getOctober();
        this.November = memberJoinVisuallizationResponseDto.getNovember();
        this.December = memberJoinVisuallizationResponseDto.getDecember();

    } // 생성자 끝
}  // Class 끝
