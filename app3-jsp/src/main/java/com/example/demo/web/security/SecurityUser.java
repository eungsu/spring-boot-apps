package com.example.demo.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.vo.User;

public class SecurityUser implements UserDetails {

	private final User user;
	
	public SecurityUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole()));
	}
	
	@Override
	public boolean isEnabled() {
		return !user.isDeleted();
	}
}
