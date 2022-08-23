package com.sezer.RentingHouse.services;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;

import java.util.List;
import java.util.UUID;

public interface CityService {

  List<City> getAll();

  City get(UUID id);

  List<Unit> getByCityId(UUID id);

  City save(City city);

  void deleteById(UUID id);
}
