package red.sukun1899.shishamo.repository

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.test.context.SpringBootTest
import red.sukun1899.shishamo.embedded.mysql.EmbeddedMySqlUtil
import spock.lang.Specification
import spock.lang.Unroll

import static com.ninja_squad.dbsetup.Operations.*

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
class ViewRepositorySpec extends Specification {
    @Autowired
    ViewRepository viewRepository

    @Autowired
    DataSourceProperties dataSourceProperties

    @Autowired
    DataSourceDestination destination

    def setupSpec() {
        if (EmbeddedMySqlUtil.enable()) {
            EmbeddedMySqlUtil.ready()
        }
    }

    def 'Get views'() {
        given: 'Prepare test tables.'
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS book'),
                sql('CREATE TABLE `book` (' +
                        '  `isbn` bigint(19) NOT NULL COMMENT \'ISBN\',' +
                        '  `title` varchar(128) NOT NULL COMMENT \'タイトル\',' +
                        '  `publisher_id` int(10) unsigned NOT NULL COMMENT \'出版社ID\',' +
                        '  PRIMARY KEY (`isbn`)' +
                        ') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=\'書籍\''),
                insertInto('book')
                        .columns("isbn", "title", "publisher_id")
                        .values(123, "FRA", 1)
                        .values(456, "USA", 2)
                        .values(789, "ENG", 3)
                        .build(),
                sql('SET foreign_key_checks = 1')
        )).launch()

        and: 'create view'
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('CREATE VIEW `view_book1` AS ' +
                        'SELECT ' +
                        '   `isbn` as `number`, ' +
                        '   `title` as `name` ' +
                        'FROM ' +
                        '   `book`' +
                        'WHERE' +
                        '   `publisher_id` > 1'),
                sql('CREATE VIEW `view_book2` AS ' +
                        'SELECT ' +
                        '   `isbn` as `number`, ' +
                        '   `publisher_id` as `pub` ' +
                        'FROM ' +
                        '   `book`' +
                        'WHERE' +
                        '   `isbn` < 200'),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def views = viewRepository.selectAll(dataSourceProperties.getSchema())

        then:
        views.size() == 2

        and:
        def firstView = views[0]
        firstView.name == 'view_book1'
        firstView.columns.size() == 2

        and:
        def secondView = views[1]
        secondView.name == 'view_book2'

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('DROP VIEW IF EXISTS `view_book1`, `view_book2`'),
                sql('SET foreign_key_checks = 1')
        )).launch()
    }

}
