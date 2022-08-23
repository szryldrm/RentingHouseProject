package com.sezer.RentingHouse.utilities.mapper;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;
import com.sezer.RentingHouse.entities.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GenericMapper {

  private final ModelMapper modelMapper;

  public GenericMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public UnitDto unitToUnitDto(Unit unit) {
    return Objects.isNull(unit) ? null : modelMapper.map(unit, UnitDto.class);
  }

  public Unit unitDtoToUnit(UnitDto unitDto) {
    return Objects.isNull(unitDto) ? null : modelMapper.map(unitDto, Unit.class);
  }

  public UnitDetailsDto unitToUnitDetailsDto(Unit unit) {
    return Objects.isNull(unit) ? null : modelMapper.map(unit, UnitDetailsDto.class);
  }

  public Unit unitCreateDtoToUnit(UnitCreateDto unitCreateDto) {
    return Objects.isNull(unitCreateDto) ? null : modelMapper.map(unitCreateDto, Unit.class);
  }

  public CityDto cityToCityDto(City city) {
    return Objects.isNull(city) ? null : modelMapper.map(city, CityDto.class);
  }

  public CityDetailsDto cityToCityDetailsDto(City city) {
    return Objects.isNull(city) ? null : modelMapper.map(city, CityDetailsDto.class);
  }

  public City cityDtoToCity(CityDto cityDto) {
    return Objects.isNull(cityDto) ? null : modelMapper.map(cityDto, City.class);
  }
}
