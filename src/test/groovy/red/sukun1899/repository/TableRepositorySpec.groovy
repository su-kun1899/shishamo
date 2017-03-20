package red.sukun1899.repository

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import red.sukun1899.AppConfig
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
import spock.lang.Specification
import spock.lang.Unroll

import static com.ninja_squad.dbsetup.Operations.sequenceOf
import static com.ninja_squad.dbsetup.Operations.sql

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
class TableRepositorySpec extends Specification {
    @Autowired
    TableRepository tableRepository

    @Autowired
    AppConfig appConfig

    @Autowired
    DataSourceDestination destination

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get tables'() {
        given: 'Prepare test tables.'
        new DbSetup(destination, sequenceOf(
                sql('DROP TABLE IF EXISTS book'),
                sql('CREATE TABLE `book` (' +
                        '  `isbn` bigint(19) NOT NULL COMMENT \'ISBN\',' +
                        '  `title` varchar(128) NOT NULL COMMENT \'タイトル\',' +
                        '  `publisherid` int(10) unsigned NOT NULL COMMENT \'出版社ID\',' +
                        '  PRIMARY KEY (`isbn`)' +
                        ') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=\'書籍\'')
        )).launch()

        when:
        def tables = tableRepository.selectAll(appConfig.getSchemaName())

        then:
        tables.size() == 1
        tables.each {
            it.getName() == 'book'
        }
    }

    def 'Get table'() {
        // FIXME untested defaultValue, nullable
        given:
        new DbSetup(destination, sequenceOf(
                sql('DROP TABLE IF EXISTS book'),
                sql('CREATE TABLE `book` (' +
                        '  `isbn` bigint(19) NOT NULL COMMENT \'ISBN\',' +
                        '  `title` varchar(128) NOT NULL COMMENT \'タイトル\',' +
                        '  `publisherid` int(10) unsigned NOT NULL COMMENT \'出版社ID\',' +
                        '  PRIMARY KEY (`isbn`)' +
                        ') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=\'書籍\'')
        )).launch()

        when:
        def table = tableRepository.select(appConfig.getSchemaName(), tableName)

        then:
        table.getName() == tableName
        table.getColumns().size() == 3

        and:
        table.getColumns().get(0).getName() == 'isbn'
        table.getColumns().get(0).getType() == 'bigint(19)'
        table.getColumns().get(0).getDefaultValue() == null
        table.getColumns().get(0).getComment() == 'ISBN'
        assert !table.getColumns().get(0).isNullable()

        and:
        table.getColumns().get(1).getName() == 'title'
        table.getColumns().get(1).getType() == 'varchar(128)'
        table.getColumns().get(1).getDefaultValue() == null
        table.getColumns().get(1).getComment() == 'タイトル'
        assert !table.getColumns().get(1).isNullable()

        and:
        table.getColumns().get(2).getName() == 'title'
        table.getColumns().get(2).getType() == 'varchar(128)'
        table.getColumns().get(2).getDefaultValue() == null
        table.getColumns().get(2).getComment() == 'タイトル'
        assert !table.getColumns().get(2).isNullable()

        where:
        tableName | _
        'book'    | _
    }
}
