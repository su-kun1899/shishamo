package red.sukun1899.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import red.sukun1899.DataSourceConfig;
import red.sukun1899.model.CreateTableStatement;
import red.sukun1899.model.ReferencedTableCount;
import red.sukun1899.model.Table;
import red.sukun1899.repository.TableRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author su-kun1899
 */
@Service
public class TableService {
  private final DataSourceConfig dataSourceConfig;
  private final TableRepository tableRepository;

  public TableService(DataSourceConfig dataSourceConfig, TableRepository tableRepository) {
    this.dataSourceConfig = dataSourceConfig;
    this.tableRepository = tableRepository;
  }

  @Transactional(readOnly = true)
  public List<Table> get() {
    return tableRepository.selectAll(dataSourceConfig.getSchemaName());
  }

  @Transactional(readOnly = true)
  public Table get(String tableName) {
    return tableRepository.select(dataSourceConfig.getSchemaName(), tableName);
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
        tableRepository.selectParentTableCountsByTableName(dataSourceConfig.getSchemaName());

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
        tableRepository.selectChildTableCountsByTableName(dataSourceConfig.getSchemaName());

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
        tableRepository.selectColumnCountsByTableName(dataSourceConfig.getSchemaName());

    return referencedTableCountMap.entrySet().stream()
        .filter(entry -> entry.getValue().getCount() > 0)
        .collect(
            Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getCount())
        );
  }
}
