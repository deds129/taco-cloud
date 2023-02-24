package sia.tacocloud.tacos.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.tacos.model.TacoOrder;
import sia.tacocloud.tacos.model.User;
import sia.tacocloud.tacos.repository.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
//@ConfigurationProperties(prefix="taco.orders")
public class OrderController {

	private final OrderRepository orderRepo;
	
	private int pageSize = 20;
	
	
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	/*
	@GetMapping
	public String ordersForUser(
			@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, pageSize);
		model.addAttribute("orders",
				orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
		return "orderList";
	}
	 */

	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors,
							   SessionStatus sessionStatus,
							   @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
