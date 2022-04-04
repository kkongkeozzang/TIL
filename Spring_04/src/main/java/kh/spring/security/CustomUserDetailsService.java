package kh.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO user = memberDAO.selectById(username);
		
		if(user != null) {
			return new User(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList(user.getRole()));
		}
		return null;
	}

}
