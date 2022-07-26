package br.com.fiap.cartao.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private JwtTokenUtils tokenUtils;
	private JwtUserDetailsService jwtUserDetailsService;
	
	private Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());
	
	public JwtRequestFilter(
			JwtTokenUtils tokenUtils,
			JwtUserDetailsService jwtUserDetailsService) {
		this.tokenUtils = tokenUtils;
		this.jwtUserDetailsService = jwtUserDetailsService;	
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		String username = null; 
		String token; 
		
		if (headerToken != null && headerToken.startsWith("Bearer ")) {
			token = headerToken.replace("Bearer ", "");
			
			try {
				username = this.tokenUtils.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				logger.info("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.info("JWT Token has expired");
			}
		} else {
			logger.info("Token does not exist or not begin with Bearer String");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
		
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			
			userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(userToken);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
