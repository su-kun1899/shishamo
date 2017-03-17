package red.sukun1899.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.sukun1899.AppConfig;
import red.sukun1899.model.Column;
import red.sukun1899.model.Table;
import red.sukun1899.repository.TableRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author su-kun1899
 */
@Service
public class TableService {
    private final AppConfig appConfig;
    private final TableRepository tableRepository;

    public TableService(AppConfig appConfig, TableRepository tableRepository) {
        this.appConfig = appConfig;
        this.tableRepository = tableRepository;
    }

    @Transactional(readOnly = true)
    public List<Table> get() {
        return tableRepository.selectAll(appConfig.getSchemaName());
    }

    @Transactional(readOnly = true)
    public Table get(String tableName) {
        // TODO dummy

        List<Column> columns = new ArrayList<>();
        Column column = new Column();
        column.setName("columnA");
        column.setDefaultValue("mysql");
        column.setNullable(false);
        column.setComment("test1");
        columns.add(column);

        column = new Column();
        column.setName("columnB");
        column.setDefaultValue("oracle");
        column.setNullable(true);
        column.setComment("test2");
        columns.add(column);

        Table table = new Table();
        table.setName("sample_table");
        table.setColumns(columns);

        return table;
    }
}
