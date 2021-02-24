package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Colecoes;
import com.amploview.program.repositories.ColecoesRepository;

@Service
public class ColecoesService {

	@Autowired
	private ColecoesRepository repository;
	
	public List<Colecoes> findAll() {
		return repository.findAll();
	}
	
	public Colecoes findById(int id) {
		Optional<Colecoes> obj =  repository.findById(id);
		return obj.get();
	}
}
