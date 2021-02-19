package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Sites;
import com.amploview.program.repositories.SitesRepository;

@Service
public class SitesService {

	@Autowired
	private SitesRepository repository;
	
	public List<Sites> findAll() {
		return repository.findAll();
	}
	
	public Sites findById(int id) {
		Optional<Sites> obj =  repository.findById(id);
		return obj.get();
	}
}
