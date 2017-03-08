package red.sukun1899.embedded.mysql;

/**
 * @author su-kun1899
 */
public class EmbeddedMySqlConfiguration {
    private int port;

    public EmbeddedMySqlConfiguration() {
        this.port = 2215;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
