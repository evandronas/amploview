package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Atributos;
import com.amploview.program.repositories.AtributosRepository;

@Service
public class AtributosService {

	@Autowired
	private AtributosRepository repository;
	
	public List<Atributos> findAll() {
		return repository.findAll();
	}
	
	public Atributos findById(int id) {
		Optional<Atributos> obj =  repository.findById(id);
		return obj.get();
	}
}
