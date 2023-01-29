package sia.tacocloud.tacos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.model.Ingredient;
import sia.tacocloud.tacos.model.Ingredient.Type;
import sia.tacocloud.tacos.model.Taco;
import sia.tacocloud.tacos.model.TacoOrder;
import sia.tacocloud.tacos.model.TacoUDT;
import sia.tacocloud.tacos.repository.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class DesignTacoController {
	
	private IngredientRepository ingredientRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> all = ingredientRepository.findAll();
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType((List<Ingredient>) all, type));

		}
	}
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	private Iterable<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors,
							  @ModelAttribute TacoOrder tacoOrder) {
		
		TacoUDT tacoUDT = TacoUDT.builder()
				.name(taco.getName())
				.ingredients(taco.getIngredients())
				.build();
		
		tacoOrder.addTaco(tacoUDT);

		if (errors.hasErrors()) {
			return "design";
		}
		
		log.info("Processing taco: {}", taco);
		return "redirect:/orders/current";
	}
}
