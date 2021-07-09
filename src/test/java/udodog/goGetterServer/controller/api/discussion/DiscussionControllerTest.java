package udodog.goGetterServer.controller.api.discussion;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.model.converter.discussion.DiscussionConverter;
import udodog.goGetterServer.model.converter.discussion.DiscussionListConverter;
import udodog.goGetterServer.service.discussion.DiscussionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DiscussionController.class)
public class DiscussionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DiscussionService discussionService;

    @MockBean
    private DiscussionConverter discussionConvertor;

    @MockBean
    private DiscussionListConverter discussionListConvertor;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 게시글_등록() throws Exception{
        String userId = "136";

        mvc.perform(post("/api/users/discussions/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"title\": \"testcode create\",\n" +
                        "  \"content\": \"testcode test\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(redirectedUrl("/api/discussions"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void 게시글_전체조회() throws Exception{
        mvc.perform(get("/api/discussions")
                .param("page", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_상세조회() throws Exception{
        String id = "70";
        String userId = "136";

        mvc.perform(get("/api/bkusers/discussions/{id}", id)
                .param("userId", userId))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_수정() throws Exception{
        String id = "70";
        String userId = "197";

        mvc.perform(patch("/api/users/discussions/edit/{id}", id).param("userId", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"title\" : \" testcode update\",\n" +
                        " \"content\" : \"testcode update\"\n" +
                        "}"))
                .andExpect(redirectedUrl("/api/bkusers/discussions/" + id + "?userId=" + userId))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void 게시글_삭제() throws Exception{
        String id = "35";
        String userId = "136";

        mvc.perform(delete("/api/users/discussions/del/{id}", id).param("userId", userId))
                .andExpect(redirectedUrl("/api/discussions"))
                .andExpect(status().is3xxRedirection());
    }
}