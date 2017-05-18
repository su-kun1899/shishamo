package red.sukun1899.controller.page

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
import red.sukun1899.model.Column
import red.sukun1899.model.Index
import red.sukun1899.model.Table
import red.sukun1899.service.IndexService
import red.sukun1899.service.TableService
import spock.lang.Specification
import spock.lang.Unroll

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

    @SpyBean
    IndexService indexService

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
        result.andReturn().modelAndView.modelMap.get('schemaName') == 'sample'
    }

    def 'Get table detail'() {
        given: 'Mocking get table'
        def table = new Table(name: 'table1')
        Mockito.doReturn(table).when(tableService).get(tableName)

        and: 'Mocking get indices'
        def indices = [
                new Index(name: 'index1', category: Index.Category.PRIMARY, columns: [new Column(name: 'hoge')]),
                new Index(name: 'index1', category: Index.Category.PERFORMANCE, columns: [new Column(name: 'fuga')])
        ]
        Mockito.doReturn(indices).when(indexService).get(tableName)

        and: 'URL'
        def url = '/tables/' + tableName

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get(url))

        then:
        result.andReturn().modelAndView.modelMap.get('table') == table
        result.andReturn().modelAndView.modelMap.get('schemaName') == 'sample'
        result.andReturn().modelAndView.modelMap.get('indices') == indices

        where:
        tableName | _
        'table1'  | _
    }
}
