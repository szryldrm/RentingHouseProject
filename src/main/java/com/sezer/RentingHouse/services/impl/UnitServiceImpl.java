package com.sezer.RentingHouse.services.impl;

import com.sezer.RentingHouse.entities.Unit;
import com.sezer.RentingHouse.repositories.UnitRepository;
import com.sezer.RentingHouse.services.UnitService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

  private final UnitRepository unitRepository;

  public UnitServiceImpl(UnitRepository unitRepository) {
    this.unitRepository = unitRepository;
  }

  /**
   * Get All Units
   *
   * @return
   */
  public List<Unit> getAll() {
    return unitRepository.findAll().stream()
        .sorted(Comparator.comparing(Unit::getId).reversed())
        .collect(Collectors.toList());
  }

  /**
   * Get Unit by Id
   *
   * @param id
   * @return
   */
  public Unit get(UUID id) {
    return unitRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Get Unit by City Id
   *
   * @param id
   * @return
   */
  public List<Unit> getByCityId(UUID id) {
    return unitRepository.findUnitsByCityId(id);
  }

  /**
   * Get Units by Parameters
   *
   * @param minArea
   * @param maxArea
   * @param minPrice
   * @param maxPrice
   * @param postalCode
   * @return
   */
  public List<Unit> getByParameters(
      Integer minArea, Integer maxArea, Integer minPrice, Integer maxPrice, String postalCode) {
    return unitRepository.findUnitsByParameters(minArea, maxArea, minPrice, maxPrice, postalCode);
  }

  /**
   * Save Unit and returning saved Unit
   *
   * @param unit
   * @return
   */
  public Unit save(Unit unit) {
    return unitRepository.save(unit);
  }

  /**
   * Delete Unit by Id
   *
   * @param id
   */
  public void deleteById(UUID id) {
    get(id);
    unitRepository.deleteById(id);
  }
}
