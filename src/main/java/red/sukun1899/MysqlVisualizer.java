package red.sukun1899;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil;

import java.util.Arrays;

@SpringBootApplication
public class MysqlVisualizer {

	public static void main(String[] args) {
		if (enableEmbeddedMysql(args)) {
			EmbeddedMySqlUtil.ready();
		}

		SpringApplication.run(MysqlVisualizer.class, args);
	}

	static boolean enableEmbeddedMysql(String[] args) {
		return Arrays.stream(args)
				.filter(s -> s.equals("--mysqlvisualizer.embedded.mysql=true"))
				.count() == 1;
	}
}
