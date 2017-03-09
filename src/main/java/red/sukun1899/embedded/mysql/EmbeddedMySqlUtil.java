package red.sukun1899.embedded.mysql;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author su-kun1899
 */
public final class EmbeddedMySqlUtil {
    private EmbeddedMySqlUtil() {
        // インスタンス生成禁止
    }

    public static EmbeddedMySqlConfiguration loadConfiguration() {
        final InputStream configAsStream = EmbeddedMySqlUtil.class.getClassLoader().getResourceAsStream("embedded-mysql.yml");
        if (configAsStream == null) {
            // Config file is not found. Use default.
            // TODO Untested
            return new EmbeddedMySqlConfiguration();
        }

        return new Yaml().loadAs(
                configAsStream,
                EmbeddedMySqlConfiguration.class
        );
    }
}
