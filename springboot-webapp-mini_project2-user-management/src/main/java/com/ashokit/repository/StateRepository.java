package com.ashokit.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.State;
@Repository
public interface StateRepository extends JpaRepository<State, Serializable> {

	public List<State> findByCountryId(Integer countryId);
}
