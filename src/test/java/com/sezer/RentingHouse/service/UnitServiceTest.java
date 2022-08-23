package com.sezer.RentingHouse.service;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;
import com.sezer.RentingHouse.repositories.UnitRepository;
import com.sezer.RentingHouse.services.impl.UnitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;

public class UnitServiceTest {

  @InjectMocks private UnitServiceImpl cut;

  @Mock private UnitRepository unitRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Test get all unit called repository")
  void should_get_all_units() {
    var unit1 = new Unit();
    unit1.setId(UUID.randomUUID());
    unit1.setTitle("5- Example Renting House");

    var unit2 = new Unit();
    unit2.setId(UUID.randomUUID());
    unit2.setTitle("6- Example Renting House");

    Mockito.when(unitRepository.findAll())
        .thenReturn(Stream.of(unit1, unit2).collect(Collectors.toList()));

    assertEquals(2, cut.getAll().size());
  }

  @Test
  @DisplayName("Test get unit called repository")
  void should_get_unit_by_id() {
    var id = "0e2df6ef-6d72-4a64-ab6a-61f2a335216c";
    var uuid = UUID.fromString(id);

    var expected = "6- Example Renting House";

    var result = new Unit();
    result.setTitle("6- Example Renting House");

    Mockito.when(unitRepository.findById(uuid)).thenReturn(Optional.of(result));

    var unit = cut.get(uuid);

    assertEquals(expected, unit.getTitle());
  }

  @Test
  @DisplayName("Test get unit called repository false")
  void should_get_unit_by_id_false() {
    var id = "0e2df6ef-6d72-4a64-ab6a-61f2a335216c";
    var uuid = UUID.fromString(id);

    var expected = "false";

    var result = new Unit();
    result.setTitle("6- Example Renting House");

    Mockito.when(unitRepository.findById(uuid)).thenReturn(Optional.of(result));

    var unit = cut.get(uuid);

    assertNotEquals(expected, unit.getTitle());
  }

  @Test
  @DisplayName("Test get units by city id")
  void should_get_units_by_city_id() {
    City city = new City();
    city.setId(UUID.fromString("45e58765-2486-462b-9213-7bb6aa0e559e"));
    city.setName("Berlin");

    var unit1 = new Unit();
    unit1.setId(UUID.fromString("0e2df6ef-6d72-4a64-ab6a-61f2a335216c"));
    unit1.setTitle("6- Example Renting House");
    unit1.setCity(city);

    var unit2 = new Unit();
    unit2.setId(UUID.fromString("5bbb3f35-b273-4ab8-bf7f-52e651ecb422"));
    unit2.setTitle("7- Example Renting House");
    unit2.setCity(city);

    Mockito.when(unitRepository.findUnitsByCityId(city.getId()))
        .thenReturn(Stream.of(unit1, unit2).collect(Collectors.toList()));

    assertEquals(2, cut.getByCityId(city.getId()).size());
  }

  @Test
  @DisplayName("Test create a unit")
  void should_create_unit() {
    var id = UUID.randomUUID();

    Unit unit = new Unit();

    unit.setId(id);
    unit.setTitle("6- Example Renting House");

    Mockito.when(unitRepository.save(unit)).thenReturn(unit);

    assertEquals(unit, cut.save(unit));
  }

  @Test
  @DisplayName("Test delete a unit by id")
  void should_delete_unit_by_id() {
    Unit unit = new Unit();
    unit.setTitle("6- Example Renting House");
    unit.setId(UUID.randomUUID());

    Mockito.when(unitRepository.findById(unit.getId())).thenReturn(Optional.of(unit));

    cut.deleteById(unit.getId());
    verify(unitRepository).deleteById(unit.getId());
  }
}
