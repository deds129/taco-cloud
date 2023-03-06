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
import sia.tacocloud.tacos.model.User;
import sia.tacocloud.tacos.repository.IngredientRepository;
import sia.tacocloud.tacos.repository.TacoRepository;
import sia.tacocloud.tacos.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@RequiredArgsConstructor
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepository;
	private TacoRepository tacoRepository;
	private UserRepository userRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository,
								TacoRepository tacoRepository,
								UserRepository userRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
		this.userRepository = userRepository;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(ingredients::add);

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name = "order")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@ModelAttribute(name = "user")
	public User user(Principal principal) {
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		return user;
	}

	private Iterable<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors,
							  @ModelAttribute TacoOrder order) {

		if (errors.hasErrors()) {
			return "design";
		}
		
		Taco saved = tacoRepository.save(taco);
		order.addTaco(saved);
		
		log.info("Processing taco: {}", taco);
		return "redirect:/orders/current";
	}
}
