package sia.tacocloud.tacos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacocloud.tacos.model.Taco;

public interface TacoPageableRepository extends PagingAndSortingRepository<Taco, Long> {
	
}
