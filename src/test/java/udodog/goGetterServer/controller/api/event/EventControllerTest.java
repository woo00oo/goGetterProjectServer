package udodog.goGetterServer.controller.api.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.model.converter.event.EventConverter;
import udodog.goGetterServer.service.event.EventService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;

    @MockBean
    private EventConverter eventConverter;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 이벤트_등록() throws Exception {

        mvc.perform(post("/api/admin/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"content\": \"20 % 할인 쿠폰 지급\",\n" +
                        "  \"end_date\": \"2021-10-15\",\n" +
                        "  \"start_date\": \"2021-10-20\",\n" +
                        "  \"title\": \"신규 가입 등록 이벤트\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void 진행중인_이벤트_전체조회() throws Exception {

        mvc.perform(get("/api/events")
                .param("page", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void 진행중인_이벤트_상세조회() throws Exception {

        Long eventId = 9L;
        mvc.perform(get("/api/events/{eventId}", eventId))
                .andExpect(status().isOk());
    }
}
