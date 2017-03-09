package red.sukun1899;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil;

@SpringBootApplication
public class MysqlVisualizerApplication {

	public static void main(String[] args) {
		// TODO Sampleの時だけ動かしたい
		EmbeddedMySqlUtil.start(EmbeddedMySqlUtil.loadConfiguration());

		SpringApplication.run(MysqlVisualizerApplication.class, args);
	}
}
