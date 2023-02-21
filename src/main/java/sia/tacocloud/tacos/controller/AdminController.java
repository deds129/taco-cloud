package sia.tacocloud.tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.tacos.service.OrderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final OrderAdminService adminService;

	public AdminController(OrderAdminService orderAdminService) {
		this.adminService = orderAdminService;
	}

	@PostMapping("/deleteOrders")
	public String deleteAllOrders() {
		adminService.deleteAllOrders();
		return "redirect:/admin";
	}
	
}
