package udodog.goGetterServer.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.enumclass.UserGrade;
import udodog.goGetterServer.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;

    public Boolean refreshTokenCheck(Long id, String refreshToken){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> {
            if(user.getToken().equals(refreshToken)){
                return true;
            }else{
                return false;
            }
        }).orElse(false);
    }

    public Map<String, UserGrade> userGradeCheck(Long id){

        Map<String, UserGrade> result = new HashMap<>();
        result.put("permission", null);

        Optional<User> optionalUser = userRepository.findById(id);

        optionalUser.ifPresent(user -> {
            result.replace("permission", user.getGrade());
        });

        return result;
    }

}
