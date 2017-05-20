package red.sukun1899.repository

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import red.sukun1899.DataSourceConfig
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
import spock.lang.Specification
import spock.lang.Unroll

import static com.ninja_squad.dbsetup.Operations.*

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
class TableRepositorySpec extends Specification {
    @Autowired
    TableRepository tableRepository

    @Autowired
    DataSourceConfig appConfig

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
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS book'),
                sql('CREATE TABLE `book` (' +
                        '  `isbn` bigint(19) NOT NULL COMMENT \'ISBN\',' +
                        '  `title` varchar(128) NOT NULL COMMENT \'タイトル\',' +
                        '  `publisherid` int(10) unsigned NOT NULL COMMENT \'出版社ID\',' +
                        '  PRIMARY KEY (`isbn`)' +
                        ') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=\'書籍\''),
                insertInto('book')
                        .columns("isbn", "title", "publisherid")
                        .values(123, "FRA", 1)
                        .values(456, "USA", 2)
                        .build(),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def tables = tableRepository.selectAll(appConfig.getSchemaName())

        then:
        tables.size() == 1
        tables.each {
            assert it.getName() == 'book'
            assert it.getComment() == '書籍'
            assert it.getRowCount() == 2
        }
    }

    def 'Get table'() {
        // FIXME untested defaultValue, nullable
        given:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql("""
                    CREATE TABLE `publisher` (
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `name` varchar(40) NOT NULL COMMENT '出版社名',
                      PRIMARY KEY (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社'
                """),
                sql('DROP TABLE IF EXISTS `book`'),
                sql("""
                    CREATE TABLE `book` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def table = tableRepository.select(appConfig.getSchemaName(), tableName)

        then:
        table.getName() == tableName
        table.getComment() == '書籍'
        table.getColumns().size() == 4

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
        table.getColumns().get(2).getName() == 'publisherid'
        table.getColumns().get(2).getType() == 'int(10) unsigned'
        table.getColumns().get(2).getDefaultValue() == null
        table.getColumns().get(2).getComment() == '出版社ID'
        assert !table.getColumns().get(2).isNullable()
        table.getColumns().get(2).getParentColumn().getTableName() == 'publisher'

        and:
        table.getColumns().get(3).getName() == 'author'
        table.getColumns().get(3).getType() == 'varchar(40)'
        table.getColumns().get(3).getDefaultValue() == null
        table.getColumns().get(3).getComment() == '著者'
        assert !table.getColumns().get(3).isNullable()

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('SET foreign_key_checks = 1')
        )).launch()

        where:
        tableName | _
        'book'    | _
    }

    def 'Get child column'() {
        given:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql("""
                    CREATE TABLE `publisher` (
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `name` varchar(40) NOT NULL COMMENT '出版社名',
                      PRIMARY KEY (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社'
                """),
                sql('DROP TABLE IF EXISTS `book`'),
                sql("""
                    CREATE TABLE `book` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('DROP TABLE IF EXISTS `book2`'),
                sql("""
                    CREATE TABLE `book2` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_2` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def table = tableRepository.select(appConfig.getSchemaName(), tableName)

        then:
        table.getName() == tableName
        table.getColumns().get(0).name == 'publisherid'
        table.getColumns().get(0).childColumns.size() == 2
        table.getColumns().get(0).childColumns.get(0).getTableName() == 'book'
        table.getColumns().get(0).childColumns.get(1).getTableName() == 'book2'

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('DROP TABLE IF EXISTS `book2`'),
                sql('SET foreign_key_checks = 1')
        )).launch()

        where:
        tableName   | _
        'publisher' | _
    }


    def 'Get parent table count'() {
        given:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql("""
                    CREATE TABLE `publisher` (
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `name` varchar(40) NOT NULL COMMENT '出版社名',
                      PRIMARY KEY (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社'
                """),
                sql('DROP TABLE IF EXISTS `book`'),
                sql("""
                    CREATE TABLE `book` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def actual = tableRepository.selectParentTableCountsByTableName(appConfig.getSchemaName())

        then:
        actual.size() == 2
        actual.get('publisher').getCount() == 0
        actual.get('book').getCount() == 1

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('SET foreign_key_checks = 1')
        )).launch()
    }

    def 'Get child table count'() {
        given:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql("""
                    CREATE TABLE `publisher` (
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `name` varchar(40) NOT NULL COMMENT '出版社名',
                      PRIMARY KEY (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社'
                """),
                sql('DROP TABLE IF EXISTS `book`'),
                sql("""
                    CREATE TABLE `book` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('DROP TABLE IF EXISTS `publisher2`'),
                sql("""
                    CREATE TABLE `publisher2` (
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `name` varchar(40) NOT NULL COMMENT '出版社名',
                      PRIMARY KEY (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社'
                """),
                sql('DROP TABLE IF EXISTS `book2`'),
                sql("""
                    CREATE TABLE `book2` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_2` FOREIGN KEY (`publisherid`) REFERENCES `publisher2` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('DROP TABLE IF EXISTS `book3`'),
                sql("""
                    CREATE TABLE `book3` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_3` FOREIGN KEY (`publisherid`) REFERENCES `publisher2` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def actual = tableRepository.selectChildTableCountsByTableName(appConfig.getSchemaName())

        then:
        actual.size() == 2
        actual.get('publisher').getCount() == 1
        actual.get('publisher2').getCount() == 2

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql('DROP TABLE IF EXISTS `publisher2`'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('DROP TABLE IF EXISTS `book2`'),
                sql('DROP TABLE IF EXISTS `book3`'),
                sql('SET foreign_key_checks = 1')
        )).launch()
    }

    def 'Get column count'() {
        given:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql("""
                    CREATE TABLE `publisher` (
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `name` varchar(40) NOT NULL COMMENT '出版社名',
                      PRIMARY KEY (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社'
                """),
                sql('DROP TABLE IF EXISTS `book`'),
                sql("""
                    CREATE TABLE `book` (
                      `isbn` bigint(19) NOT NULL COMMENT 'ISBN',
                      `title` varchar(128) NOT NULL COMMENT 'タイトル',
                      `publisherid` int(10) unsigned NOT NULL COMMENT '出版社ID',
                      `author` varchar(40) NOT NULL COMMENT '著者',
                      PRIMARY KEY (`isbn`),
                      KEY `publisherid` (`publisherid`),
                      CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisherid`) REFERENCES `publisher` (`publisherid`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='書籍'
                """),
                sql('SET foreign_key_checks = 1')
        )).launch()

        when:
        def actual = tableRepository.selectColumnCountsByTableName(appConfig.getSchemaName())

        then:
        actual.size() == 2
        actual.get('publisher').getCount() == 2
        actual.get('book').getCount() == 4

        cleanup:
        new DbSetup(destination, sequenceOf(
                sql('SET foreign_key_checks = 0'),
                sql('DROP TABLE IF EXISTS `publisher`'),
                sql('DROP TABLE IF EXISTS `book`'),
                sql('SET foreign_key_checks = 1')
        )).launch()
    }
}
