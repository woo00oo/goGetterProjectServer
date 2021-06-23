package udodog.goGetterServer.service.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import udodog.goGetterServer.model.dto.DefaultRes;
import udodog.goGetterServer.repository.UserRepository;

import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    UserService userService;
    MockHttpServletRequest httpServletRequest;
    MockHttpSession mockHttpSession;

    @Mock
    UserRepository userRepository;

    @Mock
    JavaMailSender mailSender;

    @Mock
    Executor executor;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockHttpSession = new MockHttpSession();
        httpServletRequest = new MockHttpServletRequest();
        userService = new UserService(mailSender, executor, userRepository);
    }

    @After
    public void clean(){
        mockHttpSession.clearAttributes();
    }

    @Test
    public void 이메일_중복_체크(){
        //when
        String email = "hwoo00oo96@gmail.com";

        //given
        DefaultRes defaultRes = userService.emailConfirm(httpServletRequest, email);

        //then
        assertThat(defaultRes.getMessage()).isEqualTo("성공");
    }

    @Test
    public void 발급번호_확인(){

        String number = "12345678";

        mockHttpSession.setAttribute("issuanceNum", number);

        httpServletRequest.setSession(mockHttpSession);

        DefaultRes defaultRes = userService.issuanceConfirm(httpServletRequest, number);

        assertThat(defaultRes.getMessage()).isEqualTo("일치");
    }

}