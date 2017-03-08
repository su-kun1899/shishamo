package red.sukun1899.embedded.mysql

import org.yaml.snakeyaml.Yaml
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class EmbeddedMySqlConfigurationSpec extends Specification {
    def 'portが設定されていること'() {
        when:
        def config = new Yaml().loadAs(
                '!!red.sukun1899.embedded.mysql.EmbeddedMySqlConfiguration {port: 2215}',
                EmbeddedMySqlConfiguration
        )

        then:
        config.getPort() == 2215
    }
}
