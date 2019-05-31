package com.dcyrillo.curmc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcyrillo.curmc.domain.Pagamento;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Integer>{}
