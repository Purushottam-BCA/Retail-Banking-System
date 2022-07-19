package com.cognizant.authenticationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationservice.model.AppUser;
import com.cognizant.authenticationservice.repository.UserRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {

	// Class to Implement UserDetailsService in Spring security

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

		AppUser user = null;
		user = userRepo.findById(userid).orElse(null); //.get()

		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
			return new User(user.getUserid(), user.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("Username/Password is Invalid...Please Check");
		}
	}

	public void deleteCustomer(String id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}

}
