package red.sukun1899.shishamo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import red.sukun1899.shishamo.embedded.mysql.EmbeddedMySqlUtil;

@SpringBootApplication
public class ShishamoApplication {
	public static void main(String[] args) {
		if (EmbeddedMySqlUtil.enable()) {
			EmbeddedMySqlUtil.ready();
		}

		SpringApplication.run(ShishamoApplication.class, args);
	}
}
