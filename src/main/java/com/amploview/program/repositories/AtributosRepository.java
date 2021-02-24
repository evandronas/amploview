package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amploview.program.entities.Atributos;

@Repository /* isso seria opcional */
public interface AtributosRepository extends JpaRepository<Atributos, Integer> {

}
