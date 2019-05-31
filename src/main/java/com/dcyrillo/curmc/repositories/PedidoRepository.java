package com.dcyrillo.curmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcyrillo.curmc.domain.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{}
