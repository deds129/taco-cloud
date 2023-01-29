package sia.tacocloud.tacos.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.model.Ingredient;
import sia.tacocloud.tacos.model.IngredientUDT;
import sia.tacocloud.tacos.repository.IngredientRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {
	
	private final IngredientRepository ingredientRepository;
	
	@Override
	public IngredientUDT convert(String id) {
		Optional<Ingredient> ingredient = ingredientRepository.findById(id);
		
		return ingredient.isPresent() ? ingredient
				.map(ingredient1 ->
						IngredientUDT.builder()
								.name(ingredient1.getName())
								.type(ingredient1.getType())
								.build()).get() : null;
	}
}
