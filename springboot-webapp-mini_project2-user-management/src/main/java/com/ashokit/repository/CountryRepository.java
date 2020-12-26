package com.ashokit.repository;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country, Serializable> {

}
