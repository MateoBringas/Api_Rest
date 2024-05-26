package com.example.demo.repository;

import com.example.demo.models.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Bodega,String> {
}
