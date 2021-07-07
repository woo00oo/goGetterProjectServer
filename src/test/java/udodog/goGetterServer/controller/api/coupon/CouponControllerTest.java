package udodog.goGetterServer.controller.api.coupon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import udodog.goGetterServer.config.WebMvcConfig;
import udodog.goGetterServer.service.coupon.CouponService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponController.class)
public class CouponControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponService couponService;

    @MockBean
    private WebMvcConfig webMvcConfig;

    @Test
    public void 쿠폰_등록() throws Exception {

        mvc.perform(post("/api/admin/coupons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"discount\": 30,\n" +
                        "  \"name\": \"쿠폰\",\n" +
                        "  \"quantity\": 50,\n" +
                        "  \"valid_date\": 30\n" +
                        "}"))
                .andExpect(status().isOk());
    }

}