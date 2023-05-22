package edu.aeu.msit.assigment.attendance.qr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.aeu.msit.assigment.attendance.qr.repository.UserRepository;
import edu.aeu.msit.assigment.attendance.qr.security.jwt.JwtTokenAuthenticationFilter;
import edu.aeu.msit.assigment.attendance.qr.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain springWebFilterChain(HttpSecurity http, JwtTokenProvider tokenProvider) throws Exception {
		return http.httpBasic(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/auth/signin").permitAll()
						.requestMatchers("/auth/me").permitAll()
						
						.requestMatchers("/swagger-ui/**").permitAll()
						.requestMatchers("/swagger-ui**").permitAll()
		                .requestMatchers("/swagger-resources/**").permitAll()
		                
						.requestMatchers(HttpMethod.GET, 	"/api/employees/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/employees/**").permitAll()
						.requestMatchers(HttpMethod.POST, 	"/api/employees/**").permitAll()
						.requestMatchers(HttpMethod.PUT, 	"/api/employees/**").permitAll()
						
						.requestMatchers(HttpMethod.GET, 	"/api/attendance-logs/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/attendance-logs/**").permitAll()
						.requestMatchers(HttpMethod.POST, 	"/api/attendance-logs/**").permitAll()
						.requestMatchers(HttpMethod.PUT, 	"/api/attendance-logs/**").permitAll()
						
						.requestMatchers(HttpMethod.GET, 	"/api/attendance-policies/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/attendance-policies/**").permitAll()
						.requestMatchers(HttpMethod.POST, 	"/api/attendance-policies/**").permitAll()
						.requestMatchers(HttpMethod.PUT, 	"/api/attendance-policies/**").permitAll()
						
						.requestMatchers(HttpMethod.GET, 	"/api/working-schedules/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/working-schedules/**").permitAll()
						.requestMatchers(HttpMethod.POST, 	"/api/working-schedules/**").permitAll()
						.requestMatchers(HttpMethod.PUT, 	"/api/working-schedules/**").permitAll()
						
						.requestMatchers(HttpMethod.GET, 	"/api/report/**").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	UserDetailsService customUserDetailsService(UserRepository userRepository) {
		return (username) -> userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
	}

	@Bean
	AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
		return authentication -> {
			String username = authentication.getPrincipal() + "";
			String password = authentication.getCredentials() + "";

			UserDetails user = userDetailsService.loadUserByUsername(username);

			if (!encoder.matches(password, user.getPassword())) {
				throw new BadCredentialsException("Bad credentials");
			}

			if (!user.isEnabled()) {
				throw new DisabledException("User account is not active");
			}

			return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
		};
	}

}
