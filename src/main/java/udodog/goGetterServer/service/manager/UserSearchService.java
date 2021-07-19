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

import java.util.Optional;

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

    // 회원 상세 조회
    public DefaultRes<UserSearchResponseDto> getMemberDetail (Long userId) {
        Optional<UserSearchResponseDto> memberById = userQueryRepository.findById(userId);

        if (memberById.isEmpty()) { // 상세 조회를 원하는 Member 정보가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "데이터 없음");
        } else {
            return memberById.map(userSearch -> DefaultRes.response(HttpStatus.OK.value(), "조회성공", userSearch))
                    .orElseGet(() -> DefaultRes.response(HttpStatus.OK.value(), "조회실패"));
        } // if-else문 끝

    } // getMemberDetail()끝
    // ################################# 검색 기능 ####################################

    // 회원 이름으로 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberNameSearch (String name, Pageable pageable) {
        Page<UserSearchResponseDto> memberByName = userQueryRepository.findByName(name, pageable);

        if (memberByName.isEmpty()) { // 회원 이름 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByName);
    } // memberNameSearch()끝

    // 회원 Email로 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberEmailSearch(String email, Pageable pageable) {
        Page<UserSearchResponseDto> memberByEmail = userQueryRepository.findByEmail(email, pageable);

        if (memberByEmail.isEmpty()) { // 회원 Email 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByEmail);
    } // memberEmailSearch() 끝

    // 회원 별명 검색
    public DefaultRes<Page<UserSearchResponseDto>> memberNickNameSearch(String nickName, Pageable pageable) {
        Page<UserSearchResponseDto> memberByNickName = userQueryRepository.findByNickName(nickName, pageable);

        if (memberByNickName.isEmpty()) { // 회원 Email 검색 시 결과가 없다면?
            return DefaultRes.response(HttpStatus.OK.value(), "검색 결과 없음");
        } // if문 끝

        return DefaultRes.response(HttpStatus.OK.value(), "검색완료", memberByNickName);

    } // memberNickNameSearch() 끝
} // Class 끝
