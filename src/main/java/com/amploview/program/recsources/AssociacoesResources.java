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

import com.amploview.program.entities.Associacoes;
import com.amploview.program.entities.enums.TipoAssociacao;
import com.amploview.program.services.AssociacoesService;

@RestController
@RequestMapping(value="/associacoes")
public class AssociacoesResources {
	
	@Autowired
	private AssociacoesService service;

	@GetMapping
	public ResponseEntity<List<Associacoes>> findAll() {
		List <Associacoes> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Associacoes> findById(@PathVariable Integer id) {
		Associacoes obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/{colecao}/{item}/{tipo}") //tipo='Colecoes' ou 'Grupos'
	public ResponseEntity<Associacoes> findByColecaoItem(@PathVariable String colecao, @PathVariable String item, @PathVariable String tipo) {
		Integer id_colecao = null, id_item = null, id_associacao = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Integer> query = em.createQuery("Select id from Colecoes Where descricao = '" + colecao + "'" , Integer.class);
		List<Integer> list=((javax.persistence.Query) query).getResultList();
		if (!list.isEmpty()) {
			id_colecao = list.get(0).intValue();
		}
		query = em.createQuery("Select id from " + tipo + " Where descricao = '" + item + "'" , Integer.class);
		list=((javax.persistence.Query) query).getResultList();
		if (!list.isEmpty()) {
			id_item = list.get(0).intValue();
		}
		list.clear();
		if (id_colecao != null && id_item != null) {
			query = em.createQuery("Select id from Associacoes Where colecao = " + id_colecao + " and item = " + id_item + " and tipo = " + TipoAssociacao.valueOf(tipo).getTipo() , Integer.class);
			list=((javax.persistence.Query) query).getResultList();
			if (!list.isEmpty()) {
				id_associacao = list.get(0).intValue();
			}
		}
		em.close();
		emf.close();
		if (id_associacao!=null) {
			return findById(list.get(0).intValue());
		} else {
			return null;
		}
	}
}
