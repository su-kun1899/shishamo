package red.sukun1899.shishamo.controller.page

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import red.sukun1899.shishamo.embedded.mysql.EmbeddedMySqlUtil
import red.sukun1899.shishamo.model.Column
import red.sukun1899.shishamo.model.View
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
@AutoConfigureMockMvc
class ViewControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get all table list'() {
        given:
        def views = [
                new View(name: 'view1', columns: [new Column()]),
                new View(name: 'view2', columns: [new Column(), new Column()]),
                new View(name: 'view3', columns: [new Column(), new Column(), new Column()]),
        ]
        and:
        def url = '/views'

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get(url))

        then:
        result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
        // TODO Serviceから取るようにしたら == でassertする
        result.andReturn().modelAndView.modelMap.get('views') != null
        result.andReturn().modelAndView.modelMap.get('schemaName') == 'sample'
    }

}
