package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Valores;
import com.amploview.program.repositories.ValoresRepository;

@Service
public class ValoresService {

	@Autowired
	private ValoresRepository repository;
	
	public List<Valores> findAll() {
		return repository.findAll();
	}
	
	public Valores findById(int id) {
		Optional<Valores> obj =  repository.findById(id);
		return obj.get();
	}
}
