package udodog.goGetterServer.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.dto.Pagination;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.repository.querydsl.UserQueryRepository;

@Service
@RequiredArgsConstructor
public class UserSearchService {

    // private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

    // 전체 조회
    public DefaultRes<Page<UserSearchResponseDto>> searchUserList ( Pageable pageable) { // 페이징 처리

        Page<UserSearchResponseDto> memberPage = userQueryRepository.findAll(pageable);

        if (memberPage.getTotalElements() == 0) { // 가입된 회원이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");

        } else {                                  // 가입 회원이 있다면?
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", memberPage, new Pagination(memberPage));
        } // if-else 문 끝
    } // searchUserList() 끝

    // Black 회원 전체 조회
    public DefaultRes<Page<UserSearchResponseDto>> searchBKUserList ( Pageable pageable ) { // 페이징 처리

        Page<UserSearchResponseDto> bkMemberPage = userQueryRepository.bkFindAll(pageable);

        if (bkMemberPage.getTotalElements() == 0) { // Black 회원이 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터없음");

        } else {
            return DefaultRes.response(HttpStatus.OK.value(), "조회성공", bkMemberPage, new Pagination(bkMemberPage));
        }  // if-else 문 끝
    } // searchBKUserList() 끝

} // Class 끝
