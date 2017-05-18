package red.sukun1899.service

import red.sukun1899.AppConfig
import red.sukun1899.model.Column
import red.sukun1899.model.Index
import red.sukun1899.repository.IndexRepository
import red.sukun1899.repository.TableRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class IndexServiceSpec extends Specification {
    IndexService indexService
    AppConfig appConfig
    IndexRepository indexRepository

    def setup() {
        appConfig = Mock()
        indexRepository = Mock()
        indexService = new IndexService(appConfig, indexRepository)
    }

    def 'Get indexes by table name'() {
        given: 'Mocking repository'
        appConfig.getSchemaName() >> 'schema1'
        indexRepository.selectByTableName('schema1', 'table1') >> {
            [
                    new Index(
                            name: 'index1', category: Index.Category.PRIMARY,
                            columns: [new Column(name: 'hoge')]
                    ),
                    new Index(
                            name: 'index2', category: Index.Category.UNIQUE,
                            columns: [new Column(name: 'fuga'), new Column(name: 'fuga')])
            ]
        }

        when:
        def actual = indexService.get('table1')

        then:
        actual.size() == 2
        actual.get(0).getName() == 'index1'
        actual.get(1).getName() == 'index2'
    }
}
