package com.tmoncorp.PropertyManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.repository.SecurityRepository;

/**
 * 
 * @author piro
 *
 */

@Service
public class SecurityService {
	@Autowired
	private SecurityRepository securityRepository;

	public int insertUser(String username) {
		return securityRepository.insertUser(username);
	}
}
