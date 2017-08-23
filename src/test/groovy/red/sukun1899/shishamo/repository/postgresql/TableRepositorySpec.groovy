package red.sukun1899.shishamo.repository.postgresql

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import red.sukun1899.shishamo.repository.TableRepository
import spock.lang.Specification
import spock.lang.Unroll

import static com.ninja_squad.dbsetup.Operations.*

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
@ActiveProfiles("postgresql")
class TableRepositorySpec extends Specification {
    @Autowired
    TableRepository tableRepository

    @Autowired
    DataSourceProperties dataSourceProperties

    @Autowired
    DataSourceDestination destination

    def 'Get tables'() {
        given: 'Prepare test tables.'
        new DbSetup(destination, sequenceOf(
                sql('DROP TABLE IF EXISTS book'),
                sql("""
                    CREATE TABLE book (
                      isbn bigint NOT NULL,
                      title varchar(128) NOT NULL,
                      publisherid int NOT NULL,
                      PRIMARY KEY (isbn)
                    )
                """),
                sql("COMMENT ON TABLE book IS '書籍'"),
                sql("COMMENT ON COLUMN book.title IS 'タイトル'"),
                sql("COMMENT ON COLUMN book.publisherid IS '書籍'"),
                insertInto('book')
                        .columns("isbn", "title", "publisherid")
                        .values(123, "FRA", 1)
                        .values(456, "USA", 2)
                        .build(),
        )).launch()

        when:
        def tables = tableRepository.selectAll(dataSourceProperties.getSchema())

        then:
        tables.size() == 1
        tables.each {
            assert it.getName() == 'book'
            // TODO
            //assert it.getComment() == '書籍'
            //assert it.getRowCount() == 2L
        }
    }
}