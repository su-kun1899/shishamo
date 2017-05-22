package red.sukun1899.shishamo.embedded.mysql;

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

    private static final EmbeddedMySqlConfig CONFIG;
    static {
        CONFIG = loadConfiguration();
    }

    private static final EmbeddedMysql SINGLETON_INSTANCE;
    static {
        if (enable()) {
            MysqldConfig mysqldConfig = aMysqldConfig(CONFIG.getVersion())
                    .withCharset(CONFIG.getWixCharset())
                    .withPort(CONFIG.getPort())
                    .withUser(CONFIG.getUsername(), CONFIG.getPassword())
                    .build();
            SINGLETON_INSTANCE = anEmbeddedMysql(mysqldConfig)
                    .addSchema(CONFIG.getSchemaName())
                    .start();
        } else {
            SINGLETON_INSTANCE = null;
        }
    }

    public static EmbeddedMysql ready() {
        if (!enable()) {
            throw new IllegalStateException("Embedded MySQL is disabled. " +
                    "Check your configuration file in embedded-mysql.yml");
        }
        return SINGLETON_INSTANCE;
    }

    public static boolean enable() {
        final String property = System.getProperty("shishamo.embedded.mysql");
        if (property != null){
            return Boolean.valueOf(property);
        }
        return CONFIG.isEnable();
    }

    public static EmbeddedMySqlConfig getConfig() {
        return CONFIG;
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
