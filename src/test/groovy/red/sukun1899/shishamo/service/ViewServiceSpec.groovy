package red.sukun1899.shishamo.service

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import red.sukun1899.shishamo.model.View
import red.sukun1899.shishamo.repository.ViewRepository
import spock.lang.Specification
import spock.lang.Unroll
/**
 * @author su-kun1899
 */
@Unroll
class ViewServiceSpec extends Specification {
    ViewService viewService
    DataSourceProperties dataSourceProperties
    ViewRepository viewRepository

    def setup() {
        dataSourceProperties = Mock()
        viewRepository = Mock()
        viewService = new ViewService(dataSourceProperties, viewRepository)
    }

    def 'Get all view list'() {
        given: 'Mocking repository'
        viewRepository.select('sample') >> {
            [
                    new View(name: 'view1'),
                    new View(name: 'view2'),
            ]
        }

        and:
        dataSourceProperties.getSchema() >> 'sample'

        when:
        def views = viewService.getAll()

        then:
        views.size() == 2
        views.collect {it.name} == ['view1','view2']
    }
}
