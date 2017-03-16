package red.sukun1899.controller.api.v1

import org.hamcrest.Matchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
import red.sukun1899.model.Column
import red.sukun1899.model.Table
import red.sukun1899.service.TableService
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

    @SpyBean
    TableService tableService

    def setupSpec() {
        EmbeddedMySqlUtil.ready()
    }

    def 'Get table list'() {
        setup: '期待値の用意'
        def tables = [
                new Table(name: 'table1'),
                new Table(name: 'table2'),
        ]

        and: '取得処理のMock化'
        Mockito.doReturn(tables).when(tableService).get()

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

    def 'Get table detail'() {
        setup: '期待値の用意'
        def table = new Table(
                name: tableName,
                columns: [new Column(name: columnNames[0]), new Column(name: columnNames[1])]
        )

        and: 'URL'
        def url = '/v1/tables/' + tableName

        expect:
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath('$').isNotEmpty())
                .andExpect(jsonPath('$.name').value(table.getName()))
                .andExpect(jsonPath('$.columns').isArray())
                .andExpect(jsonPath('$.columns', Matchers.hasSize(table.getColumns().size())))
                .andExpect(jsonPath('$.columns[0].name').value(table.getColumns().get(0).getName()))
                .andExpect(jsonPath('$.columns[1].name').value(table.getColumns().get(1).getName()))

        where:
        tableName      | columnNames
        'sample_table' | ['columnA', 'columnB']
    }
}
