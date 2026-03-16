package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Grad;

public interface GradRepository extends JpaRepository<Grad, Integer> {

	public Grad findByIme(String ime);
}
