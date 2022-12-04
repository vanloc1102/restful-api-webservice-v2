package com.webservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webservice.entity.UserEntity;
import com.webservice.repositories.UserRepository;
import com.webservice.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserEntity> getAllUsers() {
		return (List<UserEntity>) userRepository.findAll();
	}

	@Override
	public UserEntity findOne(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserEntity createUser(UserEntity entity) {
		return userRepository.save(entity);
	}

	@Override
	public void deleteOne(int id) {
		userRepository.deleteById(id);
	}
}
