package com.example.demo.web.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.vo.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutoLoginFilter extends OncePerRequestFilter {
	private final SecurityUser securityUser;
	
	public AutoLoginFilter() {
		User user = new User();
		user.setNo(1);
		user.setUsername("hong");
		user.setPassword("$2a$10$cGQ8YORL9m9JTvB6GciWAuni9ffWsMBBQCxChza2vuZyCa0ejI9zi"); // 비밀번호-"1234"
		user.setDeleted(false);
		user.setNickname("홍길동");
		user.setRole("ROLE_USER");
		
		this.securityUser = new SecurityUser(user);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
}
