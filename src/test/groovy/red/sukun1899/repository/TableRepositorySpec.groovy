package red.sukun1899.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import static red.sukun1899.embedded.mysql.EmbeddedMySqlUtil.loadConfiguration
import static red.sukun1899.embedded.mysql.EmbeddedMySqlUtil.start
/**
 * @author su-kun1899
 */
@Unroll
@SpringBootTest
class TableRepositorySpec extends Specification {
    @Autowired
    TableRepository tableRepository

    def setupSpec() {
        // TODO 共通化したい
        start(loadConfiguration())
    }

    def 'テーブル一覧の取得'() {
        // TODO テストデータ入れて確認
        when:
        def tables = tableRepository.find()

        then:
        tables.isEmpty()
    }
}
