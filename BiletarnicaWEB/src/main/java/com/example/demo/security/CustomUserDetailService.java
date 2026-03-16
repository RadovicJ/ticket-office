package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;

import model.Korisnik;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	KorisnikRepository kr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik k = kr.findByUsername(username);
		if (k == null) {
			throw new UsernameNotFoundException("Korisničko ime nije pronađeno");
		}
		UserDetails ud = new CustomUserDetail(k);
		return ud;
	}

}
