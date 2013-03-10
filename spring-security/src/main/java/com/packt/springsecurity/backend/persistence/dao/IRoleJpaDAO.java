package com.packt.springsecurity.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.packt.springsecurity.backend.persistence.model.Role;

public interface IRoleJpaDAO extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Role findByName(final String name);

}
