package sia.tacocloud.tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderAdminService {
	
	private final OrderRepository orderRepository;

	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAllOrders() {
		orderRepository.deleteAll();
	}
	
}
