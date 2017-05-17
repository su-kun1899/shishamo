package red.sukun1899.repository;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import red.sukun1899.model.ReferencedTableCount;
import red.sukun1899.model.Table;

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
}