package udodog.goGetterServer.controller.api.discussion;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import udodog.goGetterServer.model.converter.discussion.DiscussionReplyListConverter;
import udodog.goGetterServer.service.discussion.DiscussionReplyService;
import udodog.goGetterServer.service.discussion.DiscussionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DiscussionReplyController.class)
public class DiscussionReplyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DiscussionReplyService replyService;

    @MockBean
    private DiscussionReplyListConverter replyListConverter;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 게시글_댓글_조회() throws Exception{
        Long discussionId = 37L;

        mvc.perform(get("/api/bkusers/discussionreplies/{discussionId}", discussionId))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_댓글_등록() throws Exception{
        Long discussionId = 37L;
        Long userId = 197L;

        mvc.perform(post("/api/users/discussionreplies/{discussionId}", discussionId)
                .param("userId", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"content\" : \"create reply by testcode\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(redirectedUrl("/api/bkusers/discussions/" + discussionId + "?userId=" + userId))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void 게시글_댓글_수정() throws Exception{
        Long discussionId = 37L;
        Long replyId = 97L;
        Long userId = 457L;

        mvc.perform(patch("/api/users/discussionreplies/{discussionId}", discussionId)
                .param("replyId", String.valueOf(replyId))
                .param("userId", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"content\" : \"testcode update reply\"\n" +
                        "}"))
                .andExpect(redirectedUrl("/api/bkusers/discussions/" + discussionId + "?userId=" + userId))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void 게시글_댓글_삭제() throws Exception{
        Long discussionId = 37L;
        Long replyId = 97L;
        Long userId = 457L;

        mvc.perform(delete("/api/users/discussionreplies/{discussionId}", discussionId)
                .param("replyId", String.valueOf(replyId))
                .param("userId", String.valueOf(userId)))
                .andExpect(redirectedUrl("/api/bkusers/discussions/" + discussionId + "?userId=" + userId))
                .andExpect(status().is3xxRedirection());
    }
}