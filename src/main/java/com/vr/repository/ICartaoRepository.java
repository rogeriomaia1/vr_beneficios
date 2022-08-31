package com.vr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.model.Cartao;

@Repository
public interface ICartaoRepository extends JpaRepository<Cartao, Long> {

}
