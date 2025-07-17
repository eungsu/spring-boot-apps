package com.example.app1.web.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    try {
      String token = getJwtTokenFromRequest(request);

      if (StringUtils.hasText(token)) {
        if (jwtTokenProvider.validateToken(token)) {
          String username = jwtTokenProvider.getUsernameFromToken(token);
          Collection<? extends GrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromToken(token);
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
          authentication.setDetails(new WebAuthenticationDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }

    } catch (SignatureException ex) {
      log.error("Invalid JWT signature: {}", ex.getMessage());
      request.setAttribute("exception", ex); // 예외 정보를 request attribute에 저장
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token: {}", ex.getMessage());
      request.setAttribute("exception", ex);
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token: {}", ex.getMessage());
      request.setAttribute("exception", ex);
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token: {}", ex.getMessage());
      request.setAttribute("exception", ex);
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty or malformed: {}", ex.getMessage());
      request.setAttribute("exception", ex);
    } catch (Exception ex) { // JWT 관련 예외 외의 다른 예외 처리
      log.error("Could not set user authentication in security context: {}", ex.getMessage(), ex);
      request.setAttribute("exception", ex);
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtTokenFromRequest(HttpServletRequest httpServletRequest) {
    String bearerToken = httpServletRequest.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
