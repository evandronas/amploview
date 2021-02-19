package com.amploview.program.recsources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amploview.program.entities.Sites;

@RestController
@RequestMapping(value="/sites")
public class SitesResources {

	@GetMapping
	public ResponseEntity<Sites> findAll() {
		return ResponseEntity.ok().body(null);
	}
}
