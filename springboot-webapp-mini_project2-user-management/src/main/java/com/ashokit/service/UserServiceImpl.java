package com.ashokit.service;

import com.ashokit.entity.City;
import com.ashokit.entity.Country;
import com.ashokit.entity.State;
import com.ashokit.entity.User;
import com.ashokit.repository.CityRepository;
import com.ashokit.repository.CountryRepository;
import com.ashokit.repository.StateRepository;
import com.ashokit.repository.UserRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
	@Autowired
	CountryRepository countryRepo;
	@Autowired
	CityRepository cityRepo;
	@Autowired
	StateRepository stateRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public Map<Integer, String> findCountries() {
		List<Country> allCountry = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		allCountry.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> findStates(Integer countryId) {
		List<State> listOfState = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		listOfState.forEach(state ->{
			stateMap.put(state.getStateId(), state.getStateName());
		});
		return stateMap;
		
	}

	@Override
	public Map<Integer, String> findCities(Integer stateId) {
		  List<City> listOfCity = cityRepo.findByStateId(stateId);
		  Map<Integer, String> cityMap = new HashMap<>();
			listOfCity.forEach(city ->{
				cityMap.put(city.getCityId(), city.getCityName());
			});
			return cityMap;
			
		  
	}

	@Override
	public boolean isEmailUnique(String emailId) {
		User emailPresent = userRepo.isEmailPresent(emailId);
		if (emailPresent.equals(emailId)) {

			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveUser(User user) {
		user.setPassword(passwordGenerator());
		user.setAccStatus("locked");

		User save = userRepo.save(user);
		Integer userId = save.getUserId();
		if (userId != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String loginCheck(String email, String pwd) {
		User checkLogin = userRepo.checkLogin(email, pwd);
		String email2 = checkLogin.getEmail();
		String password = checkLogin.getPassword();
		String accStatus = checkLogin.getAccStatus();
		if (email2.equals(email) && password.equals(pwd) && accStatus.equals("unlocked")) {
			return "login is success";

		} else if (email2.equals(email) && password.equals(pwd) && accStatus.equals("locked")) {
			return "your acc is locked";
		} else {
			return "credentials are not valid";
		}

	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		User isTempValid = userRepo.checkLogin(email, tempPwd);
		if (email.equals(email) && tempPwd.equals(tempPwd)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean unlockAccount(String email, String newPwd) {
		User user = userRepo.checkLogin(email, newPwd);
		String accStatus = user.getAccStatus();
		if (accStatus.equals("locked")) {

			user.setAccStatus("unlocked");
			user.setPassword(newPwd);
			userRepo.save(user);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String forgotPassword(String email) {
		User emailPresent = userRepo.isEmailPresent(email);
		if (emailPresent.equals(email)) {
			return emailPresent.getPassword();
		} else {
			return "email is not registered with our app";
		}

	}

}
