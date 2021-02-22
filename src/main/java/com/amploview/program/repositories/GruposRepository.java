package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amploview.program.entities.Grupos;

@Repository /* isso seria opcional */
public interface GruposRepository extends JpaRepository<Grupos, Integer> {

}
