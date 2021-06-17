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
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long serialNumber;

    private Integer validDate;

    @Builder
    public Coupon(String name, Long serialNumber, Integer validDate) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.validDate = validDate;
    }
}
