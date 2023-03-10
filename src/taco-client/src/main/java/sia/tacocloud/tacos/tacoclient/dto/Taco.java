package sia.tacocloud.tacos.tacoclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {
	
	private Long id;
	
	private String name;
	
	private Date createdAt;
	
	private List<Ingredient> ingredients;
}
