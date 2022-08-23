package com.sezer.RentingHouse.entities.dtos;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDetailsDto implements Serializable {
  private Date PostedDate;
  private UUID Id;

  @NotBlank(message = "Title is Mandatory")
  private String Title;

  @NotBlank(message = "Title is Mandatory")
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
  private String PostalCode;

  @NotBlank(message = "Address is Mandatory")
  private String Address;

  @NotBlank(message = "Telephone Number is Mandatory")
  private String Tel;

  @Email private String Email;

  @NotBlank(message = "Responsible is Mandatory")
  private String Responsible;

  @NotNull(message = "Price is Mandatory")
  private Integer Price;

  private CityDto City;
}
