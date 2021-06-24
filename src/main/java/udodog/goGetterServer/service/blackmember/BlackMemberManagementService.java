package udodog.goGetterServer.service.blackmember;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.response.BlackMemberManagementResDto;
import udodog.goGetterServer.model.entity.BlackMemberManagement;
import udodog.goGetterServer.repository.BlackMemberManagementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlackMemberManagementService {

    private final BlackMemberManagementRepository blackMemberManagementRepository;      // 블랙 회원 Repository 사용을 위한 인스턴스 변수 선언


    public List<BlackMemberManagementResDto> res(Page<BlackMemberManagement> page) {

        List<BlackMemberManagementResDto> noticeList = page.stream().map(p -> )     collect(Collectors.toList());

        return noticeList;
    }


    public DefaultRes<List<BlackMemberManagementResDto>> findAllBlackMember(Pageable pageable) {

        Page<BlackMemberManagement> blackMemberPage = blackMemberManagementRepository.findByAll(pageable);

        if(blackMemberPage.getTotalElements() == 0) {
            return DefaultRes.response(HttpStatus.OK.value(), "정보없음");

        } else {
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", res(blackMemberPage), new Pagination(blackMemberPage));
        }
    }


} // Class 끝
