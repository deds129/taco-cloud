package sia.tacocloud.tacos.repository;

import sia.tacocloud.tacos.model.TacoOrder;

public interface OrderRepository {
	TacoOrder save(TacoOrder order);
}
