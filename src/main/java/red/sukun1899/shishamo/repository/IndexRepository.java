package red.sukun1899.shishamo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import red.sukun1899.shishamo.model.Index;

import java.util.List;

/**
 * @author su-kun1899
 */
@Repository
@Mapper
public interface IndexRepository {
    List<Index> selectByTableName(@Param("schemaName") String schemaName, @Param("tableName") String tableName);
}