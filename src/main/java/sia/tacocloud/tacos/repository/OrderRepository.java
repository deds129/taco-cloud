package sia.tacocloud.tacos.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.model.TacoOrder;
import sia.tacocloud.tacos.model.User;

import java.util.List;

public interface OrderRepository
		extends CrudRepository<TacoOrder, Long> {

	List<TacoOrder> findByUserOrderByPlacedAtDesc(
			User user, Pageable pageable);

}
