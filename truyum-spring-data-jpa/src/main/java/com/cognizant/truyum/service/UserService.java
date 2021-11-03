package com.cognizant.truyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.truyum.model.User;
import com.cognizant.truyum.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}
	
	@Transactional
	public User get(int id) {
		return userRepository.findById(id).get();
	}

}
