package sia.tacocloud.tacos.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Table(name = "Ingredient")
@Entity
public class Ingredient {
	
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "ingredient_name")
	private String name;

	@Column(name = "ingredient_type")
	@Enumerated(value = EnumType.STRING)
	private Type type;
	
	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}