package com.epic_energies.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epic_energies.auth.entity.ERole;
import com.epic_energies.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
