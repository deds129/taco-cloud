package sia.tacocloud.tacos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "taco_order")
public class TacoOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "placed_at")
	private Date placedAt = new Date();
	
	@NotBlank(message="Delivery name is required")
	@Size(max = 50)
	@Column(name = "delivery_name")
	private String deliveryName;
	
	@NotBlank(message="Street is required")
	@Size(max = 50)
	@Column(name = "delivery_street")
	private String deliveryStreet;
	
	@NotBlank(message="City is required")
	@Size(max = 50)
	@Column(name = "delivery_city")
	private String deliveryCity;
	
	@NotBlank(message="State is required")
	@Size(min = 2, max = 2, message = "State has to be 2 symbols long only")
	@Column(name = "delivery_state")
	private String deliveryState;
	
	@NotBlank(message="Zip code is required")
	@Size(max = 10)
	@Column(name = "delivery_zip")
	private String deliveryZip;
	
	//@CreditCardNumber(message="Not a valid credit card number")
	@NotBlank
	@Size(max = 16)
	@Column(name = "cc_number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
			message="Must be formatted MM/YY")
	@Size(max = 5)
	@Column(name = "cc_expiration")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	@Column(name = "cc_cvv")
	private String ccCVV;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}
