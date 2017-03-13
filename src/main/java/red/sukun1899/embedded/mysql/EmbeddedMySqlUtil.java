package red.sukun1899.embedded.mysql;

import com.wix.mysql.EmbeddedMysql;
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

    static final EmbeddedMySqlConfig CONFIG;
    static {
        CONFIG = loadConfiguration();
    }

    private static final EmbeddedMysql SINGLETON_INSTANCE;
    static {
        MysqldConfig mysqldConfig = aMysqldConfig(CONFIG.getVersion())
                .withCharset(CONFIG.getWixCharset())
                .withPort(CONFIG.getPort())
                .withUser(CONFIG.getUsername(), CONFIG.getPassword())
                .build();
        SINGLETON_INSTANCE = anEmbeddedMysql(mysqldConfig)
                .addSchema(CONFIG.getSchemaName())
                .start();
    }

    public static EmbeddedMysql ready() {
        return SINGLETON_INSTANCE;
    }

    private static EmbeddedMySqlConfig loadConfiguration() {
        final InputStream configAsStream = EmbeddedMySqlUtil.class.getClassLoader().getResourceAsStream("embedded-mysql.yml");
        if (configAsStream == null) {
            // Config file is not found. Use default.
            // FIXME Untested
            return new EmbeddedMySqlConfig();
        }

        return new Yaml().loadAs(
                configAsStream,
                EmbeddedMySqlConfig.class
        );
    }
}
