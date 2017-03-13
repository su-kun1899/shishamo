package red.sukun1899

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author su-kun1899
 */
@Unroll
class MysqlVisualizerApplicationSpec extends Specification {
    def 'Enable Embedded Mysql'() {
        expect:
        MysqlVisualizer.enableEmbeddedMysql(args as String[]) == enable

        where:
        args                                       || enable
        ['--mysqlvisualizer.embedded.mysql=true']  || true
        ['--mysqlvisualizer.embedded.mysql=false'] || false
        []                                         || false
    }
}
