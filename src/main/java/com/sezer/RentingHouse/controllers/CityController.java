package com.sezer.RentingHouse.controllers;

import com.sezer.RentingHouse.entities.dtos.CityDetailsDto;
import com.sezer.RentingHouse.entities.dtos.CityDto;
import com.sezer.RentingHouse.entities.dtos.UnitDto;
import com.sezer.RentingHouse.services.CityService;
import com.sezer.RentingHouse.utilities.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CityController {

  private final CityService cityService;
  private final GenericMapper genericMapper;

  public CityController(CityService cityService, GenericMapper genericMapper) {
    this.cityService = cityService;
    this.genericMapper = genericMapper;
  }

  /**
   * Returning a Json of All Cities
   *
   * @return
   */
  @GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CityDetailsDto> get() {
    return cityService.getAll().stream()
        .map(genericMapper::cityToCityDetailsDto)
        .collect(Collectors.toList());
  }

  /**
   * Returning a Json of City by Id
   *
   * @param id
   * @return
   */
  @GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CityDetailsDto getById(@PathVariable UUID id) {
    return genericMapper.cityToCityDetailsDto(cityService.get(id));
  }

  /**
   * Returning a Json of Units by City Id
   *
   * @param id
   * @return
   */
  @GetMapping(value = "/cities/{id}/units", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UnitDto> getUnitsByCityId(@PathVariable UUID id) {
    return cityService.getByCityId(id).stream()
        .map(genericMapper::unitToUnitDto)
        .collect(Collectors.toList());
  }

  /**
   * Creating a City and returning its status.
   *
   * @param cityDto
   * @return
   */
  @PostMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> add(@Valid @RequestBody CityDto cityDto) {
    cityService.save(genericMapper.cityDtoToCity(cityDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Updating a City and returning its status.
   *
   * @param cityDto
   * @return
   */
  @PutMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> update(@Valid @RequestBody CityDto cityDto) {
    cityService.save(genericMapper.cityDtoToCity(cityDto));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Deleting a City by Id and returning the status.
   *
   * @param id
   * @return
   */
  @DeleteMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> delete(@Valid @PathVariable UUID id) {
    cityService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
