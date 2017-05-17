package red.sukun1899.controller.page

import org.hamcrest.Matchers
import org.mockito.Mockito
import org.mockito.internal.matchers.And
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
import red.sukun1899.model.Table
import red.sukun1899.service.TableService
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
@AutoConfigureMockMvc
class TableControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    @SpyBean
    TableService tableService

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get all table list'() {
        given: 'Mocking get tables'
        def tables = [
                new Table(name: 'table1'),
                new Table(name: 'table2'),
        ]
        Mockito.doReturn(tables).when(tableService).get()

        and: 'Mocking get parent table count'
        def parentTableCounts = ['table1': 3, 'table2': 1]
        Mockito.doReturn(parentTableCounts)
                .when(tableService).getParentTableCountsByTableName()

        and: 'Mocking get child table count'
        def childTableCounts = ['table1': 4, 'table2': 2]
        Mockito.doReturn(childTableCounts)
                .when(tableService).getChildTableCountsByTableName()

        and: 'Mocking get column count'
        def columnCounts = ['table1': 5, 'table2': 10]
        Mockito.doReturn(columnCounts)
                .when(tableService).getColumnCountsByTableName()

        and: 'URL'
        def url = '/tables'

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get(url))

        then:
        result.andReturn().modelAndView.modelMap.get('tables') == tables
        result.andReturn().modelAndView.modelMap.get('parentTableCounts') == parentTableCounts
        result.andReturn().modelAndView.modelMap.get('childTableCounts') == childTableCounts
        result.andReturn().modelAndView.modelMap.get('columnCounts') == columnCounts
    }
}
