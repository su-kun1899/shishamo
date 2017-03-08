package red.sukun1899.embedded.mysql

import org.yaml.snakeyaml.Yaml
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class EmbeddedMySqlConfigurationSpec extends Specification {
    def 'portが設定されていること'() {
        given:
        def stream = this.getClass().getClassLoader().getResourceAsStream('embedded-mysql.yml')

        when:
        def config = new Yaml().loadAs(
                stream,
                EmbeddedMySqlConfiguration
        )

        then:
        config.getPort() == 2215
    }
}
