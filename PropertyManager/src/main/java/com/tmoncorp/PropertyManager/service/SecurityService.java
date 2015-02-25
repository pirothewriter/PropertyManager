package com.tmoncorp.PropertyManager.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.repository.SecurityRepository;

/**
 * 
 * @author piro
 *
 */

@Service
public class SecurityService implements UserDetailsService {
	@Autowired
	private SecurityRepository securityRepository;

	public int insertUser(String username) {
		return securityRepository.insertUser(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = securityRepository.getPassword(username);
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(securityRepository.getRole(username)));
		UserDetails user = new User(username, password, roles);

		return user;
	}

	public String getAuthority(String adAccount) {
		return securityRepository.getRole(adAccount);
	}

	public int grantAdmin(String adAccount) {
		return securityRepository.grantAdmin(adAccount);
	}

	public int revokeAdmin(String adAccount) {
		return securityRepository.revokeAdmin(adAccount);
	}
	
	public int revokeUser(String adAccount) {
		return securityRepository.revokeUser(adAccount);
	}
}
