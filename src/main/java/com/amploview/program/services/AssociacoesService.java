package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Associacoes;
import com.amploview.program.entities.Atributos;
import com.amploview.program.repositories.AssociacoesRepository;

@Service
public class AssociacoesService {

	@Autowired
	private AssociacoesRepository repository;
	
	public List<Associacoes> findAll() {
		return repository.findAll();
	}
	
	public Associacoes findById(int id) {
		Optional<Associacoes> obj =  repository.findById(id);
		return obj.get();
	}
}
