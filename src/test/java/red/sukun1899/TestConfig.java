package red.sukun1899;

import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author su-kun1899
 */
@Configuration
public class TestConfig {
    @Bean
    public Destination destination(DataSource dataSource) {
        return new DataSourceDestination(dataSource);
    }
}
