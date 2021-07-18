package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.user.UserFindEmailRequest;
import udodog.goGetterServer.model.dto.response.manager.search.UserSearchResponseDto;
import udodog.goGetterServer.model.dto.response.user.UserFindEmailResponseDto;
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

    // Black 회원 전체 조회
    public Page<UserSearchResponseDto> bkFindAll (Pageable pageable ) { // 페이징 처리

        List<UserSearchResponseDto> userList = queryFactory
                .select(Projections.constructor(UserSearchResponseDto.class,
                        user.id,
                        user.email,
                        user.name,
                        user.nickName,
                        user.grade,
                        user.createdAt))
                .from(user)
                .where(user.grade.eq(UserGrade.BLACK))
                .orderBy(user.name.desc())
                .fetch();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());

        return new PageImpl<>(userList.subList(start, end), pageable, userList.size());

    } // bkFindAll() 끝
}
