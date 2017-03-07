package red.sukun1899.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
class TableRepositorySpec extends Specification {
    @Autowired
    TableRepository tableRepository

    def 'テーブル一覧の取得'() {
        when:
        def tables = tableRepository.find()

        then:
        !tables.isEmpty()
    }

}
