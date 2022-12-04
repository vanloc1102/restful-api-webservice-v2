package com.webservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webservice.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{

}
