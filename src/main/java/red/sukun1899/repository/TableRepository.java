package red.sukun1899.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import red.sukun1899.model.Table;

import java.util.List;
import java.util.Map;

/**
 * @author su-kun1899
 */
@Repository
@Mapper
public interface TableRepository {
    Map<String, Object> findAll();

    List<Table> find();
}