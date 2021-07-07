package udodog.goGetterServer.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import udodog.goGetterServer.model.dto.request.user.UserFindEmailRequest;
import udodog.goGetterServer.model.dto.response.user.UserFindEmailResponseDto;

import javax.persistence.EntityManager;

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
}
