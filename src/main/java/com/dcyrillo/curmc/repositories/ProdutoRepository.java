package com.dcyrillo.curmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcyrillo.curmc.domain.Categoria;
import com.dcyrillo.curmc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{}
