package sia.tacocloud.tacos.tacoclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sia.tacocloud.tacos.tacoclient.config.TestConfig;
import sia.tacocloud.tacos.tacoclient.dto.Ingredient;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestConfig.class)
class IngredientRestClientTest {
	
	@Autowired
	IngredientRestClient ingredientRestClient;

	@Test
	void getIngredientById() {
		Ingredient flto = ingredientRestClient.getIngredientById("FLTO");
		assertThat(flto.getName()).isEqualTo("Flour Tortilla");
		assertThat(flto.getType()).isEqualTo(Ingredient.Type.WRAP);
	}

	@Test
	void updateIngredient() {
		System.out.println("hello");
	}

	@Test
	void deleteIngredient() {
		
	}

	@Test
	void createIngredient() {
		
	}
}