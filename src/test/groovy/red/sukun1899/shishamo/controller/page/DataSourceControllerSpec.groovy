package red.sukun1899.shishamo.controller.page

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import red.sukun1899.shishamo.embedded.mysql.EmbeddedMySqlUtil
import red.sukun1899.shishamo.DataSourceConfig
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
@AutoConfigureMockMvc
class DataSourceControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    DataSourceConfig dataSourceConfig

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get data source configuration.'() {
        given:
        def url = '/data-source'

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get(url))

        then:
        result.andReturn().modelAndView.modelMap.get('dataSourceConfig') == dataSourceConfig
    }
}
