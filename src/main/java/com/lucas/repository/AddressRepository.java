package com.lucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucas.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	@Query("SELECT r FROM Order r WHERE r.user.id = :userId")
	public List<Address> getAllAddress(@Param("userId")Long userId);
}
