package com.dcyrillo.curmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcyrillo.curmc.domain.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {

}
