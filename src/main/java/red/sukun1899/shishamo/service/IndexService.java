package red.sukun1899.shishamo.service;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.sukun1899.shishamo.model.Index;
import red.sukun1899.shishamo.repository.IndexRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author su-kun1899
 */
@Service
public class IndexService {
    private DataSourceProperties dataSourceProperties;
    private IndexRepository indexRepository;

    public IndexService(DataSourceProperties dataSourceProperties, IndexRepository indexRepository) {
        this.dataSourceProperties = dataSourceProperties;
        this.indexRepository = indexRepository;
    }

    @Transactional(readOnly = true)
    public List<Index> get(String tableName) {
        return indexRepository.selectByTableName(dataSourceProperties.getSchema(), tableName).stream()
                .sorted((index1, index2) -> {
                    if (index1.getCategory().order() - index2.getCategory().order() != 0) {
                        return index1.getCategory().order() - index2.getCategory().order();
                    }
                    return index1.getName().compareTo(index2.getName());
                })
                .collect(Collectors.toList());
    }
}
