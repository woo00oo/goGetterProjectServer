package udodog.goGetterServer.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@NoArgsConstructor          // 기본 생성자
@Getter
public class LibraryLocation {
    @Id
    // 기본키 생성을 DB에 위임 시켜 준다. 즉, 자동 생성(시퀀스 넘버)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryLocationId;

    private String libraryLocationCity;

    private String libraryLocationGu;

    private String libraryLocationDong;

    private String libraryLocationHouseNumber;

    private String libraryLocationStreetNumber;

    private String libraryPhoneNumber;

    @Builder
    public LibraryLocation(Long libraryLocationId, String libraryLocationCity, String libraryLocationGu, String libraryLocationDong, String libraryLocationHouseNumber, String libraryLocationStreetNumber, String libraryPhoneNumber) {
        Assert.hasText(libraryLocationCity, "libraryLocationCity must not be empty");
        Assert.hasText(libraryLocationGu, "libraryLocationGu must not be empty");
        Assert.hasText(libraryLocationDong, "libraryLocationDong must not be empty");

        this.libraryLocationId = libraryLocationId;
        this.libraryLocationCity = libraryLocationCity;
        this.libraryLocationGu = libraryLocationGu;
        this.libraryLocationDong = libraryLocationDong;
        this.libraryLocationHouseNumber = libraryLocationHouseNumber;
        this.libraryLocationStreetNumber = libraryLocationStreetNumber;
        this.libraryPhoneNumber = libraryPhoneNumber;
    } // 생성자 끝

} // Class 끝
