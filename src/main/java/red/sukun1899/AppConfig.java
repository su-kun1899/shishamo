package red.sukun1899;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author su-kun1899
 */
@Configuration
public class AppConfig {
    @Value("${spring.datasource.schema}")
    private String schemaName;

    public String getSchemaName() {
        return schemaName;
    }
}
