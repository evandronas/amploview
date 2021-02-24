package com.amploview.program.recsources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amploview.program.entities.Atributos;
import com.amploview.program.services.AtributosService;

@RestController
@RequestMapping(value="/atributos")
public class AtributosResources {
	
	@Autowired
	private AtributosService service;

	@GetMapping
	public ResponseEntity<List<Atributos>> findAll() {
		List <Atributos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Atributos> findById(@PathVariable Integer id) {
		Atributos obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/descricao/{descricao}")
	public ResponseEntity<Atributos> findByDescricao(@PathVariable String descricao) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Integer> query = em.createQuery("Select id from Atributos Where descricao = '" + descricao + "'" , Integer.class);
		@SuppressWarnings("unchecked")
		List<Integer> list=((javax.persistence.Query) query).getResultList();
		em.close();
		emf.close();
		if (!list.isEmpty()) {
			return findById(list.get(0).intValue());
		} else {
			return null;
		}
	}

}
