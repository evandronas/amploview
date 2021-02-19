package com.amploview.program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amploview.program.entities.Sites;

public interface SitesRepository extends JpaRepository<Sites, Integer> {

}
