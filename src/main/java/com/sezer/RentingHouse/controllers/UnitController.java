package com.sezer.RentingHouse.controllers;

import com.sezer.RentingHouse.entities.dtos.UnitCreateDto;
import com.sezer.RentingHouse.entities.dtos.UnitDetailsDto;
import com.sezer.RentingHouse.entities.dtos.UnitDto;
import com.sezer.RentingHouse.services.UnitService;
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
public class UnitController {

  private final UnitService unitService;
  private final GenericMapper genericMapper;

  public UnitController(UnitService unitService, GenericMapper genericMapper) {
    this.unitService = unitService;
    this.genericMapper = genericMapper;
  }

  /**
   * Returning a Json of All Units
   *
   * @return
   */
  @GetMapping(value = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UnitDto> get() {
    return unitService.getAll().stream()
        .map(genericMapper::unitToUnitDto)
        .collect(Collectors.toList());
  }

  /**
   * Returning a Json of Unit by Id
   *
   * @param id
   * @return
   */
  @GetMapping(value = "/units/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UnitDetailsDto getById(@Valid @PathVariable UUID id) {
    return genericMapper.unitToUnitDetailsDto(unitService.get(id));
  }

  @GetMapping(
      value = "/units",
      params = {"city_id"},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UnitDto> getByCityId(@RequestParam UUID city_id) {
    return unitService.getByCityId(city_id).stream()
        .map(genericMapper::unitToUnitDto)
        .collect(Collectors.toList());
  }

  /**
   * Returning a Json of Units by Parameters
   *
   * @param min_area
   * @param max_area
   * @param min_price
   * @param max_price
   * @param postal_code
   * @return
   */
  @GetMapping(value = "/units/filters", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UnitDto> getByParameters(
      @RequestParam(value = "min_area", required = false) Integer min_area,
      @RequestParam(value = "max_area", required = false) Integer max_area,
      @RequestParam(value = "min_price", required = false) Integer min_price,
      @RequestParam(value = "max_price", required = false) Integer max_price,
      @RequestParam(value = "postal_code", required = false) String postal_code) {
    return unitService
        .getByParameters(min_area, max_area, min_price, max_price, postal_code)
        .stream()
        .map(genericMapper::unitToUnitDto)
        .collect(Collectors.toList());
  }

  /**
   * Creating a Unit and returning its status.
   *
   * @param unitCreateDto
   * @return
   */
  @PostMapping(value = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UnitDetailsDto> add(@Valid @RequestBody UnitCreateDto unitCreateDto) {
    var result = unitService.save(genericMapper.unitCreateDtoToUnit(unitCreateDto));
    return new ResponseEntity<>(genericMapper.unitToUnitDetailsDto(result), HttpStatus.CREATED);
  }

  /**
   * Updating a Unit and returning its status.
   *
   * @param unitDto
   * @return
   */
  @PutMapping(value = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> update(@Valid @RequestBody UnitDto unitDto) {
    unitService.save(genericMapper.unitDtoToUnit(unitDto));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Deleting a Unit by Id and returning the status.
   *
   * @param id
   * @return
   */
  @DeleteMapping(value = "/units/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Integer> delete(@Valid @PathVariable UUID id) {
    unitService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
