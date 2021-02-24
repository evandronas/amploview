package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amploview.program.entities.Valores;

@Repository /* isso seria opcional */
public interface ValoresRepository extends JpaRepository<Valores, Integer> {

}
