package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amploview.program.entities.Colecoes;

@Repository /* isso seria opcional */
public interface ColecoesRepository extends JpaRepository<Colecoes, Integer> {

}
