package red.sukun1899.shishamo.config;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author su-kun1899
 */
@Configuration
public class MyBatisConfig {
    @Bean
    VendorDatabaseIdProvider vendorDatabaseIdProvider() {
        VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties vendorProperties = new Properties();
        vendorProperties.put("PostgreSQL", "postgresql");
        vendorProperties.put("MySQL", "mysql");
        vendorProperties.put("H2", "h2");
        databaseIdProvider.setProperties(vendorProperties);
        return databaseIdProvider;
    }
}
