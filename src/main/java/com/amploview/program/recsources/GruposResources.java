package com.amploview.program.recsources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amploview.program.entities.Grupos;
import com.amploview.program.services.GruposService;

@RestController
@RequestMapping(value="/grupos")
public class GruposResources {
	
	@Autowired
	private GruposService service;

	@GetMapping
	public ResponseEntity<List<Grupos>> findAll() {
		List <Grupos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Grupos> findById(@PathVariable Integer id) {
		Grupos obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
