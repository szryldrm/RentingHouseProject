package com.sezer.RentingHouse.services;

import com.sezer.RentingHouse.entities.Unit;

import java.util.List;
import java.util.UUID;

public interface UnitService {

  List<Unit> getAll();

  Unit get(UUID id);

  List<Unit> getByCityId(UUID id);

  List<Unit> getByParameters(
      Integer minArea, Integer maxArea, Integer minPrice, Integer maxPrice, String postalCode);

  Unit save(Unit unit);

  void deleteById(UUID id);
}
