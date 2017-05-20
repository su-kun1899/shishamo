package red.sukun1899.repository

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import red.sukun1899.DataSourceConfig
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
    DataSourceConfig appConfig

    @Autowired
    DataSourceDestination destination

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get indices by table name'() {
        given:
        new DbSetup(destination, sequenceOf(
                sql('DROP TABLE IF EXISTS book'),
                sql('CREATE TABLE `book` (' +
                        '  `isbn` bigint(19) NOT NULL COMMENT \'ISBN\',' +
                        '  `title` varchar(128) NOT NULL COMMENT \'タイトル\',' +
                        '  `memo1` varchar(128) NOT NULL COMMENT \'めも\',' +
                        '  `memo2` varchar(128) NOT NULL COMMENT \'めもめも\',' +
                        '  `remark` varchar(128) NOT NULL COMMENT \'備考\',' +
                        '  `review1` varchar(128) NOT NULL COMMENT \'レビュー\',' +
                        '  `review2` varchar(128) NOT NULL COMMENT \'レビュレビュ\',' +
                        '  `publisherid` int(10) unsigned NOT NULL COMMENT \'出版社ID\',' +
                        '  PRIMARY KEY (`isbn`),' +
                        '  UNIQUE KEY `uk_test` (`title`),' +
                        '  UNIQUE KEY `uk_test_multi` (`memo1`, `memo2`),' +
                        '  KEY `_key_test` (`remark`),' +
                        '  KEY `_key_test_multi` (`review1`, `review2`)' +
                        ') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=\'書籍\'')
        )).launch()

        when:
        def indices = indexRepository.selectByTableName(appConfig.getSchemaName(), tableName)

        then:
        indices.size() == 5

        and: 'Primary key'
        def first = indices.get(0)
        first.getName() == 'PRIMARY'
        first.getCategory() == Index.Category.PRIMARY
        first.getColumns().size() == 1
        first.getColumns().get(0).getName() == 'isbn'

        and: 'Unique key'
        def second = indices.get(1)
        second.getName() == 'uk_test'
        second.getCategory() == Index.Category.UNIQUE
        second.getColumns().size() == 1
        second.getColumns().get(0).getName() == 'title'

        and: 'Multi unique key'
        def third = indices.get(2)
        third.getName() == 'uk_test_multi'
        third.getCategory() == Index.Category.UNIQUE
        third.getColumns().size() == 2
        third.getColumns().get(0).getName() == 'memo1'
        third.getColumns().get(1).getName() == 'memo2'

        and: 'Performance key'
        def forth = indices.get(3)
        forth.getName() == '_key_test'
        forth.getCategory() == Index.Category.PERFORMANCE
        forth.getColumns().size() == 1
        forth.getColumns().get(0).getName() == 'remark'

        and: 'Multi performance key'
        def fifth = indices.get(4)
        fifth.getName() == '_key_test_multi'
        fifth.getCategory() == Index.Category.PERFORMANCE
        fifth.getColumns().size() == 2
        fifth.getColumns().get(0).getName() == 'review1'
        fifth.getColumns().get(1).getName() == 'review2'

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

    def 'Get multi primary key by table name'() {
        given:
        new DbSetup(destination, sequenceOf(
                sql('DROP TABLE IF EXISTS book'),
                sql('CREATE TABLE `book` (' +
                        '  `isbn` bigint(19) NOT NULL COMMENT \'ISBN\',' +
                        '  `title` varchar(128) NOT NULL COMMENT \'タイトル\',' +
                        '  `publisherid` int(10) unsigned NOT NULL COMMENT \'出版社ID\',' +
                        '  PRIMARY KEY (`isbn`, `title`)' +
                        ') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=\'書籍\'')
        )).launch()

        when:
        def indices = indexRepository.selectByTableName(appConfig.getSchemaName(), tableName)

        then:
        indices.size() == 1
        def first = indices.get(0)
        first.getName() == 'PRIMARY'
        first.getCategory() == Index.Category.PRIMARY
        first.getColumns().size() == 2
        first.getColumns().get(0).getName() == 'isbn'
        first.getColumns().get(1).getName() == 'title'

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
