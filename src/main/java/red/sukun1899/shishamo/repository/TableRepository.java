package red.sukun1899.shishamo.repository;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import red.sukun1899.shishamo.model.CreateTableStatement;
import red.sukun1899.shishamo.model.ReferencedTableCount;
import red.sukun1899.shishamo.model.Table;

import java.util.List;
import java.util.Map;

/**
 * @author su-kun1899
 */
@Repository
@Mapper
public interface TableRepository {
  List<Table> selectAll(String schemaName);

  Table select(@Param("schemaName") String schemaName, @Param("name") String name);

  @MapKey("baseTableName")
  Map<String, ReferencedTableCount> selectParentTableCountsByTableName(String schemaName);

  @MapKey("baseTableName")
  Map<String, ReferencedTableCount> selectChildTableCountsByTableName(String schemaName);

  @MapKey("baseTableName")
  Map<String, ReferencedTableCount> selectColumnCountsByTableName(String schemaName);

  CreateTableStatement showCreateTableStatement(Table table);
}