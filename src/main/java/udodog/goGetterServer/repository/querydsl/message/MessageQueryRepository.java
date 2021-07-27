package udodog.goGetterServer.repository.querydsl.message;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import udodog.goGetterServer.model.entity.*;

@RequiredArgsConstructor
@Repository
public class MessageQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Message findMessage(MessageRoom messageRoom){

        return queryFactory.select(QMessage.message)
                .from(QMessage.message)
                .where(QMessage.message.messageRoom.eq(messageRoom))
                .orderBy(QMessage.message.sendAt.desc())
                .limit(1)
                .fetchOne();

    }

    public User findTheOtherUser(MessageRoom messageRoom, User user){
        return queryFactory.select(QUser.user)
                .from(QMessageRoomJoin.messageRoomJoin)
                .where(QMessageRoomJoin.messageRoomJoin.messageRoom.eq(messageRoom).and(QMessageRoomJoin.messageRoomJoin.user.eq(user).not()))
                .fetchOne();
    }

}
