package red.sukun1899.repository

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import red.sukun1899.AppConfig
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
import red.sukun1899.model.Index
import spock.lang.Specification
import spock.lang.Unroll

import static com.ninja_squad.dbsetup.Operations.sequenceOf
import static com.ninja_squad.dbsetup.Operations.sql

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
class IndexRepositorySpec extends Specification {
    @Autowired
    IndexRepository indexRepository

    @Autowired
    AppConfig appConfig

    @Autowired
    DataSourceDestination destination

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get indexes By table name'() {
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
        def indexes = indexRepository.selectByTableName(appConfig.getSchemaName(), tableName)

        then:
        indexes.size() == 1
        def first = indexes.get(0)
        first.getName() == 'PRIMARY'
        first.getCategory() == Index.Category.PRIMARY
        first.getColumns().size() == 1
        first.getColumns().get(0).getName() == 'isbn'

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('SET foreign_key_checks = 1')
        )).launch()

        where:
        tableName | _
        'book'    | _
    }
}
