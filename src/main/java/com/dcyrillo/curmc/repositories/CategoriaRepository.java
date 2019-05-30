package com.dcyrillo.curmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcyrillo.curmc.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{}

