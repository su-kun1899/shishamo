package red.sukun1899;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author su-kun1899
 */
@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.schema}")
    private String schemaName;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String userName;

    public String getSchemaName() {
        return schemaName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUserName() {
        return userName;
    }
}
