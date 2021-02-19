package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amploview.program.entities.Sites;

@Repository /* isso seria opcional */
public interface SitesRepository extends JpaRepository<Sites, Integer> {

}
