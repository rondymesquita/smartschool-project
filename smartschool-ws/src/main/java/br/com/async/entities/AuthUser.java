package br.com.async.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class AuthUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Getter
	private String username;
	@Getter
	private String password;
	@Getter
	private String authToken;
	@Getter
	private P p;

}
