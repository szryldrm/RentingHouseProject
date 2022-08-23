package com.sezer.RentingHouse.service;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;
import com.sezer.RentingHouse.repositories.CityRepository;
import com.sezer.RentingHouse.services.impl.CityServiceImpl;
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

public class CityServiceTest {

  @InjectMocks private CityServiceImpl cut;

  @Mock private CityRepository cityRepository;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Test get all cities called repository")
  void should_get_all_cities() {

    var city1 = new City();
    city1.setId(UUID.fromString("2fc04c1d-f625-4732-838a-7afae59c18e2"));
    city1.setName("Stuttgart");

    var city2 = new City();
    city2.setId(UUID.fromString("45e58765-2486-462b-9213-7bb6aa0e559e"));
    city2.setName("Berlin");

    Mockito.when(cityRepository.findAll())
        .thenReturn(Stream.of(city1, city2).collect(Collectors.toList()));

    assertEquals(2, cut.getAll().size());
  }

  @Test
  @DisplayName("Test get city called repository")
  void should_get_city_by_id() {
    var id = "2fc04c1d-f625-4732-838a-7afae59c18e2";
    var uuid = UUID.fromString(id);

    var expected = "Stuttgart";

    var result = new City();
    result.setName("Stuttgart");

    Mockito.when(cityRepository.findById(uuid)).thenReturn(Optional.of(result));

    var city = cut.get(uuid);

    assertEquals(expected, city.getName());
  }

  @Test
  @DisplayName("Test get city called repository false")
  void should_get_city_by_id_false() {
    var id = "2fc04c1d-f625-4732-838a-7afae59c18e2";
    var uuid = UUID.fromString(id);

    var expected = "false";

    var result = new City();
    result.setName("Stuttgart");

    Mockito.when(cityRepository.findById(uuid)).thenReturn(Optional.of(result));

    var city = cut.get(uuid);

    assertNotEquals(expected, city.getName());
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

    Mockito.when(cityRepository.findUnitsByCityId(city.getId()))
        .thenReturn(Stream.of(unit1, unit2).collect(Collectors.toList()));

    assertEquals(2, cut.getByCityId(city.getId()).size());
  }

  @Test
  @DisplayName("Test create a city")
  void should_create_city() {
    var id = UUID.randomUUID();

    var city = new City();

    city.setId(id);
    city.setName("Dresden");

    Mockito.when(cityRepository.save(city)).thenReturn(city);

    assertEquals(city, cut.save(city));
  }

  @Test
  @DisplayName("Test delete a city by id")
  void should_delete_city_by_id() {
    City city = new City();
    city.setName("6- Example Renting House");
    city.setId(UUID.randomUUID());

    Mockito.when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));

    cut.deleteById(city.getId());
    verify(cityRepository).deleteById(city.getId());
  }
}
