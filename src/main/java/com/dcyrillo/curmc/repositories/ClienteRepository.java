package com.dcyrillo.curmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dcyrillo.curmc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
	@Transactional
	Cliente findByEmail(String email);

}
