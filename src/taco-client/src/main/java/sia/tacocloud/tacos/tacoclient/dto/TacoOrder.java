package sia.tacocloud.tacos.tacoclient.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor

public class TacoOrder {
	
	private Long id;

	private User user;
	
	private Date placedAt;
	
	private String deliveryName;
	
	private String deliveryStreet;
	
	private String deliveryCity;
	
	private String deliveryState;
	
	private String deliveryZip;
	
	private String ccNumber;
	
	private String ccExpiration;
	
	private String ccCVV;
	
	private List<Taco> tacos;
}
