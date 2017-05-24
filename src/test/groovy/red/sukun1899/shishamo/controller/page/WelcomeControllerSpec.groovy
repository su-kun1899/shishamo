package red.sukun1899.shishamo.controller.page

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification
import spock.lang.Unroll
/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
@AutoConfigureMockMvc
class WelcomeControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    def 'When request "/" then redirect to tables page'() {
        expect:
        mockMvc.perform(MockMvcRequestBuilders.get('/'))
                .andExpect(MockMvcResultMatchers.redirectedUrl('/tables'))
    }
}
