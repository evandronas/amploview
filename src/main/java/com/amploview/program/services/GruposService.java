package com.amploview.program.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amploview.program.entities.Grupos;
import com.amploview.program.repositories.GruposRepository;

@Service
public class GruposService {

	@Autowired
	private GruposRepository repository;
	
	public List<Grupos> findAll() {
		return repository.findAll();
	}
	
	public Grupos findById(int id) {
		Optional<Grupos> obj =  repository.findById(id);
		return obj.get();
	}
}
