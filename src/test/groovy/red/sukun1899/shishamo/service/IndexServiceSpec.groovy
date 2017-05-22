package red.sukun1899.shishamo.service

import red.sukun1899.shishamo.repository.IndexRepository
import red.sukun1899.shishamo.DataSourceConfig
import red.sukun1899.shishamo.model.Column
import red.sukun1899.shishamo.model.Index
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class IndexServiceSpec extends Specification {
    IndexService indexService
    DataSourceConfig appConfig
    IndexRepository indexRepository

    def setup() {
        appConfig = Mock()
        indexRepository = Mock()
        indexService = new IndexService(appConfig, indexRepository)
    }

    def 'Get indices by table name'() {
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

    def 'Indices order by category and name'() {
        given: 'Shuffle indices'
        def indices = [
                new Index(
                        name: 'a_pk', category: Index.Category.PRIMARY,
                        columns: [new Column(name: 'hoge')]
                ),
                new Index(
                        name: 'b_pk', category: Index.Category.PRIMARY,
                        columns: [new Column(name: 'fuga')]),
                new Index(
                        name: 'a_uk', category: Index.Category.UNIQUE,
                        columns: [new Column(name: 'piyo')]
                ),
                new Index(
                        name: 'b_uk', category: Index.Category.UNIQUE,
                        columns: [new Column(name: 'hogehoge')]),
                new Index(
                        name: 'a_k', category: Index.Category.PERFORMANCE,
                        columns: [new Column(name: 'fugafugafuga')]
                ),
                new Index(
                        name: 'b_k', category: Index.Category.PERFORMANCE,
                        columns: [new Column(name: 'piyopiyo')])
        ]
        Collections.shuffle(indices)

        and: 'Mocking repository'
        appConfig.getSchemaName() >> 'schema1'
        indexRepository.selectByTableName('schema1', 'table1') >> indices

        when:
        def actual = indexService.get('table1')

        then:
        actual.get(0).getName() == 'a_pk'
        actual.get(1).getName() == 'b_pk'
        actual.get(2).getName() == 'a_uk'
        actual.get(3).getName() == 'b_uk'
        actual.get(4).getName() == 'a_k'
        actual.get(5).getName() == 'b_k'
    }
}
