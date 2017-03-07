package red.sukun1899.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.sukun1899.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author su-kun1899
 */
@Service
public class TableService {
    @Transactional(readOnly = true)
    public List<Table> getAll() {
        final List<Table> tables = new ArrayList<>();
        Table table1 = new Table();
        table1.setName("table1");
        tables.add(table1);

        Table table2 = new Table();
        table2.setName("table2");
        tables.add(table2);

        return tables;
    }
}
