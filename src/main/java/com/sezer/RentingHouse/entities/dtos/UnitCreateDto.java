package com.sezer.RentingHouse.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitCreateDto implements Serializable {
  private UUID Id;

  @NotBlank(message = "Title is Mandatory")
  @Size(min = 5)
  private String Title;

  @NotBlank(message = "Title is Mandatory")
  @Size(min = 10)
  private String Description;

  @NotNull(message = "Number of Bedroom is Mandatory")
  private Integer NumberOfBedroom;

  @NotNull(message = "Number of Bathroom is Mandatory")
  private Integer NumberOfBathroom;

  @NotNull(message = "Number of Balcony is Mandatory")
  private Integer NumberOfBalcony;

  @NotNull(message = "Area is Mandatory")
  private Integer Area;

  private Integer UnitAtFloor;

  @NotBlank(message = "PostalCode is Mandatory")
  @Size(min = 5, max = 5)
  private String PostalCode;

  @NotBlank(message = "Address is Mandatory")
  @Size(min = 3)
  private String Address;

  @NotBlank(message = "Telephone Number is Mandatory")
  private String Tel;

  @Email private String Email;

  @NotBlank(message = "Responsible is Mandatory")
  @Size(min = 3, max = 100)
  private String Responsible;

  @NotNull(message = "Price is Mandatory")
  private Integer Price;

  private CityDetailsDto City;
}
