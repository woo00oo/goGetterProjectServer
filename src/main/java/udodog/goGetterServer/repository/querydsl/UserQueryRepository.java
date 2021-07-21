package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.manager.MemberJoinCountRequestDto;
import udodog.goGetterServer.model.dto.request.manager.MemberUpdateGradeRequestDto;
import udodog.goGetterServer.model.dto.request.user.UserFindEmailRequest;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.model.dto.response.manager.visuallization.MemberJoinVisuallizationResponseDto;
import udodog.goGetterServer.model.dto.response.user.UserFindEmailResponseDto;
import udodog.goGetterServer.model.entity.QUser;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static udodog.goGetterServer.model.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Transactional
    public void updatePwd(String email, String pw) {

        JPAUpdateClause updateClause = new JPAUpdateClause(em, user);

        updateClause.where(user.email.eq(email))
                .set(user.password, pw)
                .execute();
    }

    public Optional<UserFindEmailResponseDto> findByUser(UserFindEmailRequest requestDto) {

        UserFindEmailResponseDto userEail =
                queryFactory
                        .select(Projections.fields(UserFindEmailResponseDto.class,
                                user.email))
                        .from(user)
                        .where(user.name.eq(requestDto.getName()), user.phoneNumber.eq(requestDto.getPhoneNumber()))
                        .fetchOne();
        return Optional.ofNullable(userEail);
    }

    // 전체 조회
    public Page<UserSearchResponseDto> findAll ( Pageable pageable ) { // 페이징 처리

        List<UserSearchResponseDto> userList = queryFactory
                .select(Projections.constructor(UserSearchResponseDto.class,
                        user.id,
                        user.email,
                        user.name,
                        user.phoneNumber,
                        user.nickName,
                        user.grade,
                        user.createdAt))
                .from(user)
                .where(user.grade.eq(UserGrade.USER))
                .orderBy(user.name.desc())
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());

        return new PageImpl<>(userList.subList(start, end), pageable, userList.size());

    } // findAll() 끝

    // Member 식별번호로 상세정보 조회
    public Optional<UserSearchResponseDto> findById (Long userId) {
        UserSearchResponseDto userSearchResponseDto = queryFactory.select(Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                                .where(user.id.eq(userId))
                                .fetchOne();

        return Optional.ofNullable(userSearchResponseDto);
    } // findById() 끝

    public Optional<User> findByUserId (Long userId) {
        User user = queryFactory.selectFrom(QUser.user)
                                .where(QUser.user.id.eq(userId))
                                .fetchOne();

        return Optional.ofNullable(user);

    } // findById()

    public Optional<User> findByUserGrade(UserGrade userGrade, Long userId) {

        User user = queryFactory.selectFrom(QUser.user)
                                .where(QUser.user.id.eq(userId))
                                .fetchOne();

        return Optional.ofNullable(user);

    } // findByUserGrade() 끝

    //회원 등급 변경 Method
    @Transactional
    public void updateMemberGrade(MemberUpdateGradeRequestDto memberUpdateGradeRequestDto, Long userId) {

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, user);

        jpaUpdateClause.where(user.id.eq(userId))
                       .set(user.grade, memberUpdateGradeRequestDto.getUserGrade())
                        .execute();
    } // updateMemberGrade() 끝

    // Black 회원 강제 탈퇴 Method
    @Transactional
    public void blackMemberWithdrawal(Long userId, UserGrade userGrade) {

        JPADeleteClause deleteClause = new JPADeleteClause(em, user);

        deleteClause.where(user.id.eq(userId)).execute();
    } // blackMemberWithdrawal() 끝

    // 회원 가입일 전체 조회

    public Optional<List<MemberJoinVisuallizationResponseDto>> findByCreatedAt(MemberJoinCountRequestDto requestDto) {

        List<MemberJoinVisuallizationResponseDto> selectJoinCount =
                queryFactory
                        .select(Projections.constructor(MemberJoinVisuallizationResponseDto.class,
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count(),
                                user.createdAt.yearMonth().count()
                        ))
                        .from(user)
                        .where(user.createdAt.year().eq(requestDto.getYear()))
                        .orderBy(user.createdAt.yearMonth().asc())
                        .groupBy(user.createdAt.yearMonth())
                        .fetch();

        return Optional.ofNullable(selectJoinCount);
    } // findByCreatedAt() 끝

    // ######################## 검색 기능 ###########################

    // 회원 이름으로 검색
    public Page<UserSearchResponseDto> findByName(String name, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                                .where(user.name.contains(name))
                                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());
    } // findByName()끝

    // 회원 Email로 검색
    public Page<UserSearchResponseDto> findByEmail(String email, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                .where(user.email.contains(email))
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());

    } // findByEmail() 끝

    // 회원 별명 검색
    public Page<UserSearchResponseDto> findByNickName(String nickName, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                .where(user.nickName.contains(nickName))
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());
    } // findByNickName()끝

    // 회원 등급 검색
    public Page<UserSearchResponseDto> findByGrade(UserGrade userGrade, Pageable pageable) {

        List<UserSearchResponseDto> userSearchResponseDtoList = queryFactory.select( Projections.constructor(UserSearchResponseDto.class,
                user.id,
                user.email,
                user.name,
                user.phoneNumber,
                user.nickName,
                user.grade,
                user.createdAt)).from(user)
                .where(user.grade.eq(userGrade))
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), userSearchResponseDtoList.size());

        return new PageImpl<>(userSearchResponseDtoList.subList(start, end), pageable, userSearchResponseDtoList.size());

    } // findByGrade() 끝
} // Class 끝
