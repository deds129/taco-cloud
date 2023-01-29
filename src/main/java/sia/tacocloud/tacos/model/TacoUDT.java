package sia.tacocloud.tacos.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@Builder
@UserDefinedType("taco")
public class TacoUDT {
	
	private final String name;
	
	private final List<IngredientUDT> ingredients;
}
