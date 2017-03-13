package red.sukun1899.service

import red.sukun1899.model.Table
import red.sukun1899.repository.TableRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class TableServiceSpec extends Specification {
    TableService tableService
    TableRepository tableRepository

    def setup() {
        tableRepository = Mock()
        tableService = new TableService(tableRepository)
    }

    def 'テーブル一覧の取得'() {
        given: 'repositoryのMock化'
        tableRepository.selectAll() >> {
            [
                    new Table(name: 'table1'),
                    new Table(name: 'table2'),
            ]
        }

        when:
        def tables = tableService.get()

        then:
        tables.size() == 2
        tables[0].getName() == 'table1'
        tables[1].getName() == 'table2'
    }
}
