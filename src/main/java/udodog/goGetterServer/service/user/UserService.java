package udodog.goGetterServer.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.model.entity.User;
import udodog.goGetterServer.model.utils.MailHandler;
import udodog.goGetterServer.repository.UserRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String FROM_ADDRESS = "torvlf1@gamil.com"; // 본인 google 메일로 변경
    private final JavaMailSender mailSender;
    private final Executor executor;
    private final UserRepository userRepository;


    public DefaultRes emailConfirm(HttpServletRequest request, String email){

        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.map(user -> DefaultRes.response(HttpStatus.OK.value(), "이메일중복"))
                .orElseGet(()->{
                    CompletableFuture.runAsync(()->sendMail(request, email), executor);
                    return DefaultRes.response(HttpStatus.OK.value(), "전송성공");
                });

    }

    public void sendMail(HttpServletRequest request, String email){

        HttpSession session = request.getSession();

        String issuanceNum = "";

        for (int i = 0; i < 8; i++){
            issuanceNum += Integer.toString(new Random().nextInt(10));
        }
        session.setAttribute("issuanceNum", issuanceNum);
        session.setMaxInactiveInterval(5*60); //세션 만료 시간 5분

        String htmlContent = "<h2><b>안녕하세요&nbsp;</b></h2>" +
                "<h2><b>Go-getter 입니다.</b><h2>"+
                "<h3><br></h3><p><h2>본인확인을 위해 다음 인증 번호를 화면에 입력해주세요.</h2></p>" +
                "<p><h2><span style=\"background-color: rgb(255, 255, 0);\">"+issuanceNum+"</span><h2></p>" +
                "<h3><br></h3><p></p><p><h2>감사합니다.</h2></p>" +
                "<br><br><img src='cid:go-getter-img' width=90 height=90>";

        try{
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setFrom(FROM_ADDRESS);
            mailHandler.setSubject("[go-getter] 본인인증 확인");
            mailHandler.setText(htmlContent,true);
            mailHandler.setInline("go-getter-img","static/go-getter.jpg");

            mailHandler.send();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

    }

    public DefaultRes issuanceConfirm(HttpServletRequest request, String number){
        HttpSession session = request.getSession();
        String issuanceNum = (String) session.getAttribute("issuanceNum");

        if (issuanceNum== null){

            return DefaultRes.response(HttpStatus.OK.value(), "불일치");

        }else if(issuanceNum.equals(number)){

            return DefaultRes.response(HttpStatus.OK.value(), "일치");

        }else {

            return DefaultRes.response(HttpStatus.OK.value(), "불일치");

        }


    }


}
