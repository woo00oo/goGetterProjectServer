package udodog.goGetterServer.service.sharingboard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.entity.SharingBoardLike;
import udodog.goGetterServer.repository.SharingBoardLikeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SharingBoardLikeService {

    private final SharingBoardLikeRepository sharingBoardLikeRepository;

    public DefaultRes likeFeature(Long boardId, Long userId) {

        Optional<SharingBoardLike> sharingBoardLike = sharingBoardLikeRepository.findByUserId(userId);

        if(sharingBoardLike.isPresent()){
            sharingBoardLikeRepository.delete(sharingBoardLike.get());
            return DefaultRes.response(HttpStatus.OK.value(),"좋아요 취소");
        }
        else{
            SharingBoardLike like = SharingBoardLike.builder()
                    .userId(userId)
                    .sharingBoardId(boardId)
                    .build();

            sharingBoardLikeRepository.save(like);

            return DefaultRes.response(HttpStatus.OK.value(),"좋아요 등록");
        }

    }
}
