package red.sukun1899.embedded.mysql;

import com.wix.mysql.distribution.Version;

/**
 * @author su-kun1899
 */
public class EmbeddedMySqlConfiguration {
    private int port;
    private Version version;
    private String username;
    private String password;
    private String schemaName;

    public EmbeddedMySqlConfiguration() {
        this.port = 3306;
        this.version = Version.v5_7_latest;
        this.username = "root";
        this.password = "";
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
}
