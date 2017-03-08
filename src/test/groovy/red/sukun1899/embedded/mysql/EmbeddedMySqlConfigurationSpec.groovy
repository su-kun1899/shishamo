package red.sukun1899.embedded.mysql

import org.yaml.snakeyaml.Yaml
import spock.lang.Specification

import static com.wix.mysql.distribution.Version.v5_7_latest

/**
 * @author su-kun1899
 */
class EmbeddedMySqlConfigurationSpec extends Specification {
    def 'yamlから設定を取得できること'() {
        given:
        def stream = this.getClass().getClassLoader().getResourceAsStream('embedded-mysql.yml')

        when:
        def config = new Yaml().loadAs(
                stream,
                EmbeddedMySqlConfiguration
        )

        then:
        config.getPort() == 2215
        config.getVersion() == v5_7_latest
    }
}
