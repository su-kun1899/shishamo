package red.sukun1899.shishamo.embedded.mysql;

import com.wix.mysql.config.Charset;
import com.wix.mysql.distribution.Version;

import static com.wix.mysql.config.Charset.LATIN1;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.Charset.UTF8MB4;

/**
 * @author su-kun1899
 */
public class EmbeddedMySqlConfig {
    private boolean enable;
    private int port;
    private Version version;
    private String username;
    private String password;
    private String schemaName;
    private String charset;

    public EmbeddedMySqlConfig() {
        this.port = 3306;
        this.version = Version.v5_7_latest;
        this.username = "root";
        this.schemaName = "test";
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public Charset getWixCharset() {
        if (getCharset().equalsIgnoreCase(UTF8.getCharset())) {
            return UTF8;
        } else if (getCharset().equalsIgnoreCase(UTF8MB4.getCharset())) {
            return UTF8MB4;
        } else if (getCharset().equalsIgnoreCase(LATIN1.getCharset())) {
            return LATIN1;
        } else {
            // default
            return UTF8;
        }
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
