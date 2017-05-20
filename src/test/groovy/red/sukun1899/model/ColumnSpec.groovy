package red.sukun1899.model

import spock.lang.Specification

/**
 * @author su-kun1899
 */
class ColumnSpec extends Specification {
    def 'Get index category'() {
        setup:
        def column = new Column(name: columnName)

        and:
        def indices = [
                new Index(category: Index.Category.PRIMARY, columns: [new Column(name: 'test1'), new Column(name: 'test2')]),
                new Index(category: Index.Category.UNIQUE, columns: [new Column(name: 'test1'), new Column(name: 'test3')]),
                new Index(category: Index.Category.PERFORMANCE, columns: [new Column(name: 'test4'), new Column(name: 'test5')])
        ]

        expect:
        column.getIndexCategory(indices) == category

        where:
        columnName || category
        'test1'    || Index.Category.PRIMARY
        'test3'    || Index.Category.UNIQUE
        'test4'    || Index.Category.PERFORMANCE
        'test99'   || null
    }

}
