package red.sukun1899.shishamo.service

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import red.sukun1899.shishamo.model.Column
import red.sukun1899.shishamo.model.CreateTableStatement
import red.sukun1899.shishamo.model.ReferencedTableCount
import red.sukun1899.shishamo.model.Table
import red.sukun1899.shishamo.model.json.ColumnDetail
import red.sukun1899.shishamo.repository.TableRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class TableServiceSpec extends Specification {
    TableService tableService
    DataSourceProperties dataSourceProperties
    TableRepository tableRepository

    def setup() {
        dataSourceProperties = Mock()
        tableRepository = Mock()
        tableService = new TableService(dataSourceProperties, tableRepository)
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

    def 'Get table overview'() {
        given: 'Spy service'
        tableService = Spy(TableService, constructorArgs: [dataSourceProperties, tableRepository])
        tableService.getParentTableCountsByTableName() >> ['sample_table': 1L]
        tableService.getChildTableCountsByTableName() >> ['sample_table': 2L]
        tableService.getColumnCountsByTableName() >> ['sample_table': 10L]

        and: 'Mocking repository'
        def expected = new Table(
                name: 'sample_table',
                rowCount: 10L,
                columns: [
                        new Column(name: 'columnA', defaultValue: 'mysql', nullable: false, comment: 'test1'),
                        new Column(name: 'columnB', defaultValue: 'oracle', nullable: true, comment: 'test2'),
                ]
        )
        tableRepository.selectAll(*_) >> [expected]

        when:
        def overviews = tableService.getOverView()

        then:
        assert overviews.get(0).getName() == expected.getName()
        assert overviews.get(0).getComment() == expected.getComment()
        assert overviews.get(0).getCountOfRows() == expected.getRowCount()
        assert overviews.get(0).getCountOfParents() == 1L
        assert overviews.get(0).getCountOfChildren() == 2L
        assert overviews.get(0).getCountOfColumns() == 10L
        assert overviews.get(0).getUrl() == '/api/v1/tables/' + expected.getName()
    }

    def 'Get table overview which has no relation'() {
        given: 'Spy service'
        tableService = Spy(TableService, constructorArgs: [dataSourceProperties, tableRepository])
        tableService.getParentTableCountsByTableName() >> ['hoge': 1L]
        tableService.getChildTableCountsByTableName() >> ['fuga': 2L]
        tableService.getColumnCountsByTableName() >> ['piyo': 10L]

        and: 'Mocking repository'
        def expected = new Table(
                name: 'sample_table',
                rowCount: 10L,
                columns: [
                        new Column(name: 'columnA', defaultValue: 'mysql', nullable: false, comment: 'test1'),
                        new Column(name: 'columnB', defaultValue: 'oracle', nullable: true, comment: 'test2'),
                ]
        )
        tableRepository.selectAll(*_) >> [expected]

        when:
        def overviews = tableService.getOverView()

        then:
        assert overviews.get(0).getCountOfParents() == 0L
        assert overviews.get(0).getCountOfChildren() == 0L
        assert overviews.get(0).getCountOfColumns() == 0L
    }

    def 'Get table'() {
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

    def 'Get table detail'() {
        given:'Prepare table'
        def table = new Table(
                name: 'sample_table',
                columns: [
                        new Column(name: 'columnA', defaultValue: 'mysql', nullable: false, comment: 'test1'),
                        new Column(name: 'columnB', defaultValue: 'oracle', nullable: true, comment: 'test2'),
                ],
                comment: 'Sample comment.',
                rowCount: 10
        )

        and: 'Spy service'
        tableService = Spy(TableService, constructorArgs: [dataSourceProperties, tableRepository])
        tableService.get('table1') >> table

        when:
        def detail = tableService.getDetail('table1')

        then:
        detail.getName() == table.getName()
        detail.getComment() == table.getComment()
        detail.getCountOfRows() == table.getRowCount()
        detail.getUrl() == "/api/v1/tables/${table.getName()}"
        detail.getColumns().size() == table.getColumns().size()
        detail.getColumns().eachWithIndex { ColumnDetail column, int i ->
            assert column.getName() == table.getColumns().get(i).getName()
            assert column.getDefaultValue() == table.getColumns().get(i).getDefaultValue()
            assert column.getType() == table.getColumns().get(i).getType()
            assert column.isNullable() == table.getColumns().get(i).isNullable()
            assert column.getComment() == table.getColumns().get(i).getComment()
        }
    }

    def 'Get parent table count'() {
        given:
        tableRepository.selectParentTableCountsByTableName(_) >> {
            [
                    'book'     : new ReferencedTableCount(baseTableName: 'book', count: 1),
                    'publisher': new ReferencedTableCount(baseTableName: 'publisher', count: 0),
            ]
        }

        when:
        def actual = tableService.getParentTableCountsByTableName()

        then:
        actual.size() == 1
        actual.get('book') == 1L
        actual.get('publisher') == null
    }

    def 'Get child table count'() {
        given:
        tableRepository.selectChildTableCountsByTableName(_) >> {
            [
                    'book'     : new ReferencedTableCount(baseTableName: 'book', count: 1),
                    'publisher': new ReferencedTableCount(baseTableName: 'publisher', count: 0),
            ]
        }

        when:
        def actual = tableService.getChildTableCountsByTableName()

        then:
        actual.size() == 1
        actual.get('book') == 1L
        actual.get('publisher') == null
    }

    def 'Get column count'() {
        given:
        tableRepository.selectColumnCountsByTableName(_) >> {
            [
                    'book'     : new ReferencedTableCount(baseTableName: 'book', count: 1),
                    'publisher': new ReferencedTableCount(baseTableName: 'publisher', count: 0),
            ]
        }

        when:
        def actual = tableService.getColumnCountsByTableName()

        then:
        actual.size() == 1
        actual.get('book') == 1L
        actual.get('publisher') == null
    }

    def 'Get create table statement'() {
        given:
        def createTableStatement = new CreateTableStatement(tableName: 'book', ddl: 'Create table book')
        tableRepository.showCreateTableStatement(_) >> createTableStatement

        when:
        def actual = tableService.getCreateTableStatement(new Table(name: 'book'))

        then:
        actual.getTableName() == createTableStatement.getTableName()
        actual.getDdl() == createTableStatement.getDdl()
    }
}
