package br.com.async.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity(name = "tb_myuser")
@Data
public class MyUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "user_seq", sequenceName = "user_seq")
	@GeneratedValue(generator = "user_seq", strategy = GenerationType.AUTO)
	@Id
	private Integer code;
	private String username;
	private String password;
	
//	public MyUser(){
//		
//	}
//	public Integer getCode() {
//		return code;
//	}
//	public void setCode(Integer code) {
//		this.code = code;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + ((username == null) ? 0 : username.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		MyUser other = (MyUser) obj;
//		if (password == null) {
//			if (other.password != null)
//				return false;
//		} else if (!password.equals(other.password))
//			return false;
//		if (username == null) {
//			if (other.username != null)
//				return false;
//		} else if (!username.equals(other.username))
//			return false;
//		return true;
//	}

}
