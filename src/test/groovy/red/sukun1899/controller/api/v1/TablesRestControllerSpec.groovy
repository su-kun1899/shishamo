package red.sukun1899.controller.api.v1

import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import red.sukun1899.model.Table
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
@AutoConfigureMockMvc
class TablesRestControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    def 'テーブル一覧の取得'() {
        setup: '期待値の用意'
        def tables = [
                new Table(name: 'table1'),
                new Table(name: 'table2'),
        ]

        and: 'URL'
        def url = '/v1/tables'

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath('$').isNotEmpty())
                .andExpect(jsonPath('$').isArray())
                .andExpect(jsonPath('$', Matchers.hasSize(tables.size())))
                .andExpect(jsonPath('$[0].name').value(tables[0].getName()))
                .andExpect(jsonPath('$[1].name').value(tables[1].getName()))
    }
}
