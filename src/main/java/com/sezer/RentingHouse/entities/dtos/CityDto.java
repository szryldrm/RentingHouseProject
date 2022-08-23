package com.sezer.RentingHouse.entities.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
  @NotBlank(message = "City Name is Mandatory")
  private String Name;
}
