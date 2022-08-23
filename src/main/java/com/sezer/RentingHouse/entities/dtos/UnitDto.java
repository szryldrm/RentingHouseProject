package com.sezer.RentingHouse.entities.dtos;

import lombok.*;

import java.sql.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDto {
  private UUID Id;

  private String Title;

  private Integer Area;

  private String PostalCode;

  private String Address;

  private String Tel;

  private Integer Price;

  private Date PostedDate;
  private CityDto City;
}
