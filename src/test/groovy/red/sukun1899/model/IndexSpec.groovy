package red.sukun1899.model

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class IndexSpec extends Specification {
    def 'Get index category by column'() {
        given:
        def column = new Column(name: columnName)
        def index = new Index(
                category: indexCategory,
                columns: [
                        new Column(name: 'test1'),
                        new Column(name: 'test2'),
                        new Column(name: 'test3'),
                ]
        )

        when:
        def actual = index.getCategoryByColumn(column)

        then:
        actual == expected

        where:
        indexCategory              | columnName || expected
        Index.Category.PRIMARY     | 'test1'    || Index.Category.PRIMARY
        Index.Category.UNIQUE      | 'test2'    || Index.Category.UNIQUE
        Index.Category.PERFORMANCE | 'test3'    || Index.Category.PERFORMANCE
        Index.Category.PRIMARY     | 'test99'   || null
    }
}
