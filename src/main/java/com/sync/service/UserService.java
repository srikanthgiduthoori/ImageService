package com.sync.service;

import java.util.ArrayList;
import java.util.List;

import com.sync.mapper.UserMapper;
import com.sync.vo.UserVO;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sync.exception.JWTTokenException;
import com.sync.model.Image;
import com.sync.model.User;
import com.sync.repository.ImageRepository;
import com.sync.repository.UserRepository;
import com.sync.util.JwtTokenUtil;
import com.sync.vo.JwtRequest;
import com.sync.vo.JwtResponse;
import com.sync.vo.UserProfile;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private ImageRepository imageRepository;

	private final UserMapper usermapper = Mappers.getMapper(UserMapper.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserVO save(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return usermapper.toUserVO(userRepository.save((user)));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new JWTTokenException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new JWTTokenException("INVALID_CREDENTIALS", "Please provide correct Username/Password");
		}
	}

	public JwtResponse createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());

		String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse jwtResponse = new JwtResponse(token);
		
		return jwtResponse;

	}

	public UserProfile findByUsername(String name) {
		
		List<Image> images = imageRepository.findByUsername(name);
		User user = userRepository.findByUsername(name);
		UserProfile userprofile = new UserProfile();
		userprofile.setImages(images);
		userprofile.setUser(UserMapper.INSTANCE.toUserVO(user));
		return userprofile;
	}

}