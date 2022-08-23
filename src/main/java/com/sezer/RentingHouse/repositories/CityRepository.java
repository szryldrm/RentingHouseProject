package com.sezer.RentingHouse.repositories;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
  /**
   * Find Units by City Id
   *
   * @param id
   * @return
   */
  @Query("SELECT u FROM Unit u WHERE u.City.Id = :id")
  List<Unit> findUnitsByCityId(@Param("id") UUID id);
}
