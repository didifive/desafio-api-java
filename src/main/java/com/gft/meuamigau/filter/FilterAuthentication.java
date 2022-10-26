package com.gft.meuamigau.filter;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.services.AuthenticationService;
import com.gft.meuamigau.services.UserService;

public class FilterAuthentication extends OncePerRequestFilter {

	private AuthenticationService authenticationService;

	private UserService userService;

	public FilterAuthentication(AuthenticationService authenticationService, UserService userService) {
		this.authenticationService = authenticationService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		String token = null;
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7, header.length());
		}

		if (authenticationService.verificaToken(token)) {
			Long idUser = authenticationService.retornarIdUser(token);
			User user = userService.findById(idUser,ENTITY).getEntity();
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
		}

		filterChain.doFilter(request, response);
	}

}
