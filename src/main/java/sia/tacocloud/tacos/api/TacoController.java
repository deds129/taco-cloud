package sia.tacocloud.tacos.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.model.Taco;
import sia.tacocloud.tacos.repository.TacoPageableRepository;
import sia.tacocloud.tacos.repository.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos",
		produces="application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {
	private final TacoPageableRepository tacoPageableRepository;
	private final TacoRepository tacoRepository;
	
	public TacoController(TacoPageableRepository tacoPageableRepository, TacoRepository tacoRepository) {
		this.tacoPageableRepository = tacoPageableRepository;
		this.tacoRepository = tacoRepository;
	}
	
	@GetMapping(params="recent")
	@ResponseBody()
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(
				0, 12, Sort.by("createdAt").descending());
		return tacoPageableRepository.findAll(page).getContent();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepository.findById(id);
		return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
	}

	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepository.save(taco);
	}
	
	
}
