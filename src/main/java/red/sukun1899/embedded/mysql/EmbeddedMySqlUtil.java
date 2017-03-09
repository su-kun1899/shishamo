package red.sukun1899.embedded.mysql;

import com.wix.mysql.config.MysqldConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;

/**
 * @author su-kun1899
 */
public final class EmbeddedMySqlUtil {
    private EmbeddedMySqlUtil() {
        // インスタンス生成禁止
    }

    public static void start(EmbeddedMySqlConfig config) {
        MysqldConfig mysqldConfig = aMysqldConfig(config.getVersion())
                .withCharset(config.getWixCharset())
                .withPort(config.getPort())
                .withUser(config.getUsername(), config.getPassword())
                .build();
        anEmbeddedMysql(mysqldConfig)
                .addSchema(config.getSchemaName())
                .start();
    }

    public static EmbeddedMySqlConfig loadConfiguration() {
        final InputStream configAsStream = EmbeddedMySqlUtil.class.getClassLoader().getResourceAsStream("embedded-mysql.yml");
        if (configAsStream == null) {
            // Config file is not found. Use default.
            // TODO Untested
            return new EmbeddedMySqlConfig();
        }

        return new Yaml().loadAs(
                configAsStream,
                EmbeddedMySqlConfig.class
        );
    }
}
