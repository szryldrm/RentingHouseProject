package com.sezer.RentingHouse.services.impl;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;
import com.sezer.RentingHouse.repositories.CityRepository;
import com.sezer.RentingHouse.services.CityService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {

  private final CityRepository cityRepository;

  public CityServiceImpl(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  /**
   * Get All Cities
   *
   * @return
   */
  public List<City> getAll() {
    return cityRepository.findAll();
  }

  /**
   * Get City by Id
   *
   * @param id
   * @return
   */
  public City get(UUID id) {
    return cityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Get Units by City Id
   *
   * @param id
   * @return
   */
  public List<Unit> getByCityId(UUID id) {
    return cityRepository.findUnitsByCityId(id);
  }

  /**
   * Save city and returning saved city
   *
   * @param city
   * @return
   */
  public City save(City city) {
    return cityRepository.save(city);
  }

  /**
   * Delete City by Id
   *
   * @param id
   */
  public void deleteById(UUID id) {
    cityRepository.deleteById(id);
  }
}
