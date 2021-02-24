package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Elementos;
import com.amploview.program.repositories.ElementosRepository;

@Service
public class ElementosService {

	@Autowired
	private ElementosRepository repository;
	
	public List<Elementos> findAll() {
		return repository.findAll();
	}
	
	public Elementos findById(int id) {
		Optional<Elementos> obj =  repository.findById(id);
		return obj.get();
	}
}
