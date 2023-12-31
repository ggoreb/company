package com.example.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.company.model.Locations;

@Repository
public interface LocationsRepository 
    extends JpaRepository<Locations, Integer> {
  
  Locations findByStateProvince(String stateProvince);

}



