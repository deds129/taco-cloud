package sia.tacocloud.tacos.tacoclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import sia.tacocloud.tacos.tacoclient.client.IngredientRestClient;

@Configuration
@ComponentScan({"sia.tacocloud.tacos.tacoclient"})
public class TestConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IngredientRestClient ingredientRestClient(RestTemplate restTemplate) {
		return new IngredientRestClient(restTemplate);
	}
}
