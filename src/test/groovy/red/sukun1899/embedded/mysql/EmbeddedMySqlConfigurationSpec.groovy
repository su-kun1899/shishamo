package red.sukun1899.embedded.mysql

import spock.lang.Specification

/**
 * @author su-kun1899
 */
class EmbeddedMySqlConfigurationSpec extends Specification {
    def 'portが設定されていること'() {
        when:
        def config = new EmbeddedMySqlConfiguration();

        then:
        config.getPort() == 2215
    }
}
