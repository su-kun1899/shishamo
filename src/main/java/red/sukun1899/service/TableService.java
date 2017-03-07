package red.sukun1899.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.sukun1899.model.Table;
import red.sukun1899.repository.TableRepository;

import java.util.List;

/**
 * @author su-kun1899
 */
@Service
public class TableService {
    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Transactional(readOnly = true)
    public List<Table> getAll() {
        return tableRepository.find();
    }
}
