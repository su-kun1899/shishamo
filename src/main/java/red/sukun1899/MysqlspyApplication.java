package red.sukun1899;

import com.wix.mysql.config.MysqldConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_latest;

@SpringBootApplication
public class MysqlspyApplication {

	public static void main(String[] args) {
		// TODO Sampleの時だけ動かしたい
		MysqldConfig mysqldConfig = aMysqldConfig(v5_6_latest)
				.withCharset(UTF8)
				.withPort(2215)
				.withUser("sampleUser", "samplePassword")
				.build();
		anEmbeddedMysql(mysqldConfig)
				.addSchema("sample")
				.start();

		SpringApplication.run(MysqlspyApplication.class, args);
	}
}
