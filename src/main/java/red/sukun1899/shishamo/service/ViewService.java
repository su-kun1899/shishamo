package red.sukun1899.shishamo.service;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import red.sukun1899.shishamo.model.View;
import red.sukun1899.shishamo.repository.ViewRepository;

import java.util.List;

/**
 * @author su-kun1899
 */
@Service
public class ViewService {
    private final DataSourceProperties dataSourceProperties;
    private final ViewRepository viewRepository;

    public ViewService(DataSourceProperties dataSourceProperties, ViewRepository viewRepository) {
        this.dataSourceProperties = dataSourceProperties;
        this.viewRepository = viewRepository;
    }

    public List<View> getAll() {
        return viewRepository.select(dataSourceProperties.getSchema());
    }

    public View getByName(String name) {
        return viewRepository.selectByName(dataSourceProperties.getSchema(), name);
    }
}
