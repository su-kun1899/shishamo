package red.sukun1899.shishamo.service;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.sukun1899.shishamo.model.CreateTableStatement;
import red.sukun1899.shishamo.model.ReferencedTableCount;
import red.sukun1899.shishamo.model.Table;
import red.sukun1899.shishamo.repository.TableRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author su-kun1899
 */
@Service
public class TableService {
  private final DataSourceProperties dataSourceProperties;
  private final TableRepository tableRepository;

  public TableService(DataSourceProperties dataSourceProperties, TableRepository tableRepository) {
    this.dataSourceProperties = dataSourceProperties;
    this.tableRepository = tableRepository;
  }

  @Transactional(readOnly = true)
  public List<Table> get() {
    return tableRepository.selectAll(dataSourceProperties.getSchema());
  }

  @Transactional(readOnly = true)
  public Table get(String tableName) {
    return tableRepository.select(dataSourceProperties.getSchema(), tableName);
  }

  @Transactional(readOnly = true)
  public CreateTableStatement getCreateTableStatement(Table table) {
    return tableRepository.showCreateTableStatement(table);
  }

  /**
   * @return Key: tableName, Value: Parent table's count
   */
  public Map<String, Long> getParentTableCountsByTableName() {
    Map<String, ReferencedTableCount> referencedTableCountMap =
        tableRepository.selectParentTableCountsByTableName(dataSourceProperties.getSchema());

    return referencedTableCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().getCount() > 0)
        .collect(
            Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getCount())
        );
  }

  /**
   * @return Key: tableName, Value: Child table's count
   */
  public Map<String, Long> getChildTableCountsByTableName() {
    Map<String, ReferencedTableCount> referencedTableCountMap =
        tableRepository.selectChildTableCountsByTableName(dataSourceProperties.getSchema());

    return referencedTableCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().getCount() > 0)
        .collect(
            Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getCount())
        );
  }

  /**
   * @return Key: tableName, Value: Column count
   */
  public Map<String, Long> getColumnCountsByTableName() {
    Map<String, ReferencedTableCount> referencedTableCountMap =
        tableRepository.selectColumnCountsByTableName(dataSourceProperties.getSchema());

    return referencedTableCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().getCount() > 0)
        .collect(
            Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getCount())
        );
  }
}
