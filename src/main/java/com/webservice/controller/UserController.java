package com.webservice.controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservice.entity.UserEntity;
import com.webservice.service.UserService;
import com.webservice.service.util.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public List<UserEntity> getUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("users-all")
	public CollectionModel<UserEntity> getHyperUsers() {
		List<UserEntity> users = userService.getAllUsers();
		if (!CollectionUtils.isEmpty(users)) {
			for (UserEntity userEntity : users) {
				userEntity.removeLinks();
				int id = userEntity.getId();
				Link selfLink = linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel();
				userEntity.add(selfLink);
			}
		}
		Link link = linkTo(methodOn(UserController.class).getHyperUsers())
                .withSelfRel();
		CollectionModel<UserEntity> result = CollectionModel.of(users, link);
		return result;
	}
	
	@GetMapping("users/{id}")
	public UserEntity getOneUser(@PathVariable int id) {
		UserEntity entity = userService.findOne(id);
		if (entity == null) {
			throw new UserNotFoundException("id:" + id);
		}
		return entity;
	}
	
	@PostMapping("users/create")
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity entity) {
		UserEntity userEntity = userService.createUser(entity);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userEntity.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/delete/{id}")
	public void deleteOne(@PathVariable int id) {
		userService.deleteOne(id);
	}
}
