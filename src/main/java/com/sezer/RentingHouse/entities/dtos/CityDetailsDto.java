package com.sezer.RentingHouse.entities.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDetailsDto {
  private UUID Id;

  @NotBlank(message = "City Name is Mandatory")
  private String Name;
}
