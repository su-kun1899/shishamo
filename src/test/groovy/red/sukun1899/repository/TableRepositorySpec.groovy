package red.sukun1899.repository

import com.wix.mysql.config.MysqldConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql
import static com.wix.mysql.config.Charset.UTF8
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig
import static com.wix.mysql.distribution.Version.v5_6_latest

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
        MysqldConfig mysqldConfig = aMysqldConfig(v5_6_latest)
                .withCharset(UTF8)
                .withPort(2215)
                .withUser("sampleUser", "samplePassword")
                .build()
        anEmbeddedMysql(mysqldConfig)
                .addSchema("sample")
                .start()
    }

    def 'テーブル一覧の取得'() {
        // TODO テストデータ入れて確認
        when:
        def tables = tableRepository.find()

        then:
        tables.isEmpty()
    }
}
