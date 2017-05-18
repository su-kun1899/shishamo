package red.sukun1899.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import red.sukun1899.AppConfig;
import red.sukun1899.model.Index;
import red.sukun1899.model.Table;
import red.sukun1899.repository.IndexRepository;

/**
 * @author su-kun1899
 */
@Service
public class IndexService {
  private AppConfig appConfig;
  private IndexRepository indexRepository;

  public IndexService(AppConfig appConfig, IndexRepository indexRepository) {
    this.appConfig = appConfig;
    this.indexRepository = indexRepository;
  }

  @Transactional(readOnly = true)
  public List<Index> get(String tableName) {
    return indexRepository.selectByTableName(appConfig.getSchemaName(), tableName);
  }
}
