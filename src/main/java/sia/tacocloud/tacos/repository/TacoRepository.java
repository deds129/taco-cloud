package sia.tacocloud.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
