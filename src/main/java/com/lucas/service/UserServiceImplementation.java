package com.lucas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucas.config.JwtProvider;
import com.lucas.exception.UserException;
import com.lucas.model.User;
import com.lucas.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User>user=userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not found whit id:"+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		User user= userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("user nos found whit email:"+email);
		}
		return user;
	}

}
