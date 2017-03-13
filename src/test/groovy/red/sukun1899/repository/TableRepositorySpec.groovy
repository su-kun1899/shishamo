package red.sukun1899.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil
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

    def setupSpec() {
        EmbeddedMySqlUtil.ready()
    }

    def 'テーブル一覧の取得'() {
        // TODO テストデータ入れて確認
        when:
        def tables = tableRepository.selectAll('sample')

        then:
        tables.isEmpty()
    }
}
