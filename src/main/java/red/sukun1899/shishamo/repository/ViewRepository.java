package red.sukun1899.shishamo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import red.sukun1899.shishamo.model.View;

import java.util.List;

/**
 * @author su-kun1899
 */
@Repository
@Mapper
public interface ViewRepository {
    List<View> select(@Param("schemaName") String schemaName);

    View selectByName(@Param("schemaName") String schemaName, @Param("name") String name);
}
