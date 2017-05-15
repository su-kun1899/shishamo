package red.sukun1899;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import red.sukun1899.embedded.mysql.EmbeddedMySqlUtil;

@SpringBootApplication
public class ShishamoApplication {
	public static void main(String[] args) {
		if (EmbeddedMySqlUtil.enable()) {
			EmbeddedMySqlUtil.ready();
		}

		SpringApplication.run(ShishamoApplication.class, args);
	}
}
