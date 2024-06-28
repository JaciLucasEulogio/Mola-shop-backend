package com.lucas.service;

import com.lucas.exception.UserException;
import com.lucas.model.User;

public interface UserService {
	public User findUserById(Long userId) throws UserException;
	public User findUserProfileByJwt(String jwt) throws UserException; 
}
