package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amploview.program.entities.Elementos;

@Repository /* isso seria opcional */
public interface ElementosRepository extends JpaRepository<Elementos, Integer> {

}
