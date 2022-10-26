package com.gft.meuamigau.config;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_AUTH;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gft.meuamigau.filter.FilterAuthentication;
import com.gft.meuamigau.services.AuthenticationService;
import com.gft.meuamigau.services.UserService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui"
			, "/configuration/security", "/swagger-ui.html", "/webjars/**"
			// -- Swagger UI v3 (OpenAPI)
			, "/v3/api-docs/**", "/swagger-ui/**", "/api-docs/**"
			// other public endpoints of your API may be appended to this array
	};
    
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http
    								, AuthenticationService authenticationService
    								, UserService userService) throws Exception {
        	http.authorizeRequests()
        	.antMatchers(HttpMethod.GET, "/").permitAll()
        	.antMatchers(AUTH_WHITELIST).permitAll()
        	.antMatchers(HttpMethod.POST, PATH_AUTH).permitAll()
        	.and().authorizeRequests()
    		.anyRequest().authenticated()
    		.and().csrf().disable()
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.addFilterBefore(new FilterAuthentication(authenticationService, userService),
    				UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
