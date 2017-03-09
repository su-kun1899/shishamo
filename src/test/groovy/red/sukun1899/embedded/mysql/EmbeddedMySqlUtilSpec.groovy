package red.sukun1899.embedded.mysql

import com.wix.mysql.config.Charset
import spock.lang.Specification
import spock.lang.Unroll

import static com.wix.mysql.distribution.Version.v5_7_latest
/**
 * @author su-kun1899
 */
@Unroll
class EmbeddedMySqlUtilSpec extends Specification {
    def 'yamlから設定を取得できること'() {
        when:
        def config = EmbeddedMySqlUtil.loadConfiguration()

        then:
        config.getWixCharset() == Charset.UTF8
        config.getPort() == 2215
        config.getVersion() == v5_7_latest
        config.getUsername() == 'sampleUser'
        config.getPassword() == 'samplePassword'
        config.getSchemaName() == 'sample'
    }
}
