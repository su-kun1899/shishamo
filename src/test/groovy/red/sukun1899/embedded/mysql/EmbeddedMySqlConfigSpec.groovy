package red.sukun1899.embedded.mysql

import spock.lang.Specification

import static com.wix.mysql.config.Charset.LATIN1
import static com.wix.mysql.config.Charset.UTF8
import static com.wix.mysql.config.Charset.UTF8MB4

/**
 * @author su-kun1899
 */
class EmbeddedMySqlConfigSpec extends Specification {
    def 'Get charset config'() {
        given:
        def config = new EmbeddedMySqlConfig(charset: param)

        when:
        def charsetConfig = config.getWixCharset()

        then:
        charsetConfig == charset

        where:
        param     || charset
        'UTF8'    || UTF8
        'utf8'    || UTF8
        'LATIN1'  || LATIN1
        'latin1'  || LATIN1
        'UTF8MB4' || UTF8MB4
        'utf8mb4' || UTF8MB4
        ''        || UTF8
        'dummy'   || UTF8
    }
}
