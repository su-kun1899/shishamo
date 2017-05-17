package red.sukun1899.service

import red.sukun1899.AppConfig
import red.sukun1899.model.Column
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
    AppConfig appConfig
    TableRepository tableRepository

    def setup() {
        appConfig = Mock()
        tableRepository = Mock()
        tableService = new TableService(appConfig, tableRepository)
    }

    def 'Get table list'() {
        given: 'Mocking repository'
        tableRepository.selectAll(_) >> {
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

    def 'Get table detail'() {
        given: 'Mocking repository'
        def expected = new Table(
                name: 'sample_table',
                columns: [
                        new Column(name: 'columnA', defaultValue: 'mysql', nullable: false, comment: 'test1'),
                        new Column(name: 'columnB', defaultValue: 'oracle', nullable: true, comment: 'test2'),
                ]
        )
        tableRepository.select(*_) >> expected

        when:
        def table = tableService.get(expected.getName())

        then:
        assert table.getName() == expected.getName()
        assert table.getColumns().size() == expected.getColumns().size()
        table.getColumns().eachWithIndex { Column column, int i ->
            assert column.getName() == expected.getColumns().get(i).getName()
            assert column.getDefaultValue() == expected.getColumns().get(i).getDefaultValue()
            assert column.isNullable() == expected.getColumns().get(i).isNullable()
            assert column.getComment() == expected.getColumns().get(i).getComment()
        }
    }

    def 'Get parent table count'() {
        given:
        tableRepository.selectParentTableCountsByTableName(_) >> ['book': 1, 'publisher': 0]

        when:
        def actual = tableService.getParentTableCountsByTableName()

        then:
        actual.size() == 2
        actual.get('book') == 1
        actual.get('publisher') == 0
    }
}
