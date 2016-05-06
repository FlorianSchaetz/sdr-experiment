package de.irian.learning.sdr.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import de.irian.learning.sdr.data.person.entity.Login;
import de.irian.learning.sdr.data.person.repository.LoginRepository;

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String loginName = authentication.getName();
		String password = authentication.getCredentials().toString();

		Login login = loginRepository.findByNameAndPassword(loginName, password);
		if (login != null) {

			List<SimpleGrantedAuthority> grantedAuthorities = Arrays.asList( new SimpleGrantedAuthority("user"));

			return new UsernamePasswordAuthenticationToken(login, login.getPassword(), grantedAuthorities);
		}

		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
