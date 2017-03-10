package red.sukun1899.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import red.sukun1899.model.Table;

import java.util.List;

/**
 * @author su-kun1899
 */
@Repository
@Mapper
public interface TableRepository {
    List<Table> find();
}