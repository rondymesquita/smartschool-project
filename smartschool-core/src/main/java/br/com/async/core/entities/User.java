package br.com.async.core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@Data
@Entity (name = "tb_user")
@EqualsAndHashCode(callSuper=false)
public class User extends AbstractEntity implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "user_seq", sequenceName = "user_seq")
	@GeneratedValue(generator = "user_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person")
	private Person person;
	
	@Getter
	private String username;
	
	@Getter
	private String password;
	
	@Getter
	private boolean accountNonExpired = true;
	
	@Getter
	private boolean accountNonLocked = true;
	
	@Getter
	private boolean credentialsNonExpired = true;
	
	@Getter
	private boolean enabled = true;
	
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> authorities;
	
	public static boolean isAuthenticated(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	

}
