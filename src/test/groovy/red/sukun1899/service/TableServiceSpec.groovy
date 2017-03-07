package red.sukun1899.service

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class TableServiceSpec extends Specification {
    TableService tableService

    def setup() {
        tableService = new TableService()
    }

    def 'テーブル一覧の取得'() {
        when:
        def tables = tableService.getAll()

        then:
        tables.size() == 2
        tables[0].getName() == 'table1'
        tables[1].getName() == 'table2'
    }
}
