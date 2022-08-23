package com.sezer.RentingHouse.repositories;

import com.sezer.RentingHouse.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {

  /**
   * Find Units by City Id
   *
   * @param id
   * @return
   */
  @Query("SELECT u FROM Unit u WHERE u.City.Id = :id")
  List<Unit> findUnitsByCityId(@Param("id") UUID id);

  /**
   * find Units by Parameters
   *
   * @param minArea
   * @param maxArea
   * @param minPrice
   * @param maxPrice
   * @param postalCode
   * @return
   */
  @Query(
      value =
          "SELECT * FROM units as u WHERE "
              + "(:minarea IS NULL OR u.area >= :minarea)"
              + "AND (:maxarea IS NULL OR u.area <= :maxarea)"
              + "AND (:minprice IS NULL OR u.price >= :minprice)"
              + "AND (:maxprice IS NULL OR u.price <= :maxprice)"
              + "AND (:postalcode IS NULL OR u.postal_code = :postalcode)",
      nativeQuery = true)
  List<Unit> findUnitsByParameters(
      @Param("minarea") Integer minArea,
      @Param("maxarea") Integer maxArea,
      @Param("minprice") Integer minPrice,
      @Param("maxprice") Integer maxPrice,
      @Param("postalcode") String postalCode);
}
