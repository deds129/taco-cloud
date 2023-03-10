package sia.tacocloud.tacos.tacoclient.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.management.relation.Role;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor
public class User {
	
	private Long id;
	
	private final String fullname;
	
	private final String street;
	
	private final String city;
	
	private final String state;
	
	private final String zip;
	
	private final String phoneNumber;
	
	private Set<Role> roles;
	
}
