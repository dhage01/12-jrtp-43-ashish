package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.City;
import com.ashokit.entity.User;

@Repository
public interface CityRepository extends JpaRepository<City, Serializable>{

	public List<City> findByStateId(Integer stateId);
}
