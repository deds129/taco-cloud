package sia.tacocloud.tacos.tacoclient.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sia.tacocloud.tacos.tacoclient.dto.Ingredient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class IngredientRestClient {
	
	private final RestTemplate restTemplate;

	public IngredientRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	//See method using in tests
	
	public Ingredient getIngredientById(String ingredientId) {
		return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}",
				Ingredient.class, ingredientId);
	}

	public Ingredient getIngredientByIdUsingMap(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		return restTemplate.getForObject("http://localhost:8080/ingredients/{id}",
				Ingredient.class, urlVariables);
	}

	public Ingredient getIngredientByIdUsingURI(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/ingredients/{id}")
				.build(urlVariables);
		return restTemplate.getForObject(url, Ingredient.class);
	}

	public Ingredient getIngredientByIdWrappedByResponseEntity(String ingredientId) {
		ResponseEntity<Ingredient> responseEntity =
				restTemplate.getForEntity("http://localhost:8080/ingredients/{id}",
						Ingredient.class, ingredientId);
		log.info("Fetched time: {}",
				responseEntity.getHeaders().getDate());
		return responseEntity.getBody();
	}

	public void updateIngredient(Ingredient ingredient) {
		restTemplate.put("http://localhost:8080/data-api/ingredients/{id}",
				ingredient, ingredient.getId());
	}

	public void deleteIngredient(Ingredient ingredient) {
		restTemplate.delete("http://localhost:8080/data-api/ingredients/{id}",
				ingredient.getId());
	}

	public Ingredient createIngredient(Ingredient ingredient) {
		return restTemplate.postForObject("http://localhost:8080/data-api/ingredients",
				ingredient, Ingredient.class);
	}
	
}
