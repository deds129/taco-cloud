package sia.tacocloud.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.model.TacoOrder;

import java.util.UUID;

public interface OrderRepository
		extends CrudRepository<TacoOrder, UUID> {

}
