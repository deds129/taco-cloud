package sia.tacocloud.tacos.tacoclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sia.tacocloud.tacos.tacoclient.config.TestConfig;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfig.class)
class TacoClientApplicationTests {

	@Test
	void contextLoads() {
	}

}
