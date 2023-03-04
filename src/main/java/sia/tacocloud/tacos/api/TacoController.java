package sia.tacocloud.tacos.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.model.Taco;
import sia.tacocloud.tacos.repository.TacoPageableRepository;

@RestController
@RequestMapping(path="/api/tacos",
		produces="application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {
	private TacoPageableRepository tacoRepo;
	
	public TacoController(TacoPageableRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(params="recent")
	@ResponseBody()
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(
				0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(page).getContent();
	}
}
