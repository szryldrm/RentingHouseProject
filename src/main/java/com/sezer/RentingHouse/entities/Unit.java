package com.sezer.RentingHouse.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "units")
public class Unit {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", insertable = false, updatable = false, nullable = false)
  private UUID Id;

  @NotBlank(message = "Title is Mandatory")
  @Column(name = "title")
  private String Title;

  @NotBlank(message = "Title is Mandatory")
  @Column(name = "description")
  private String Description;

  @NotNull(message = "Number of Bedroom is Mandatory")
  @Column(name = "number_of_bedroom")
  private Integer NumberOfBedroom;

  @NotNull(message = "Number of Bathroom is Mandatory")
  @Column(name = "number_of_bathroom")
  private Integer NumberOfBathroom;

  @NotNull(message = "Number of Balcony is Mandatory")
  @Column(name = "number_of_balcony")
  private Integer NumberOfBalcony;

  @NotNull(message = "Area is Mandatory")
  @Column(name = "area")
  private Integer Area;

  @Column(name = "unit_at_floor")
  private Integer UnitAtFloor;

  @NotBlank(message = "PostalCode is Mandatory")
  @Column(name = "postal_code")
  private String PostalCode;

  @NotBlank(message = "Address is Mandatory")
  @Column(name = "address")
  private String Address;

  @NotBlank(message = "Telephone Number is Mandatory")
  @Column(name = "tel")
  private String Tel;

  @javax.validation.constraints.Email
  @Column(name = "email")
  private String Email;

  @NotBlank(message = "Responsible is Mandatory")
  @Column(name = "responsible")
  private String Responsible;

  @NotNull(message = "Price is Mandatory")
  @Column(name = "price")
  private Integer Price;

  @Column(name = "posted_date")
  private Date PostedDate;

  @ManyToOne
  @JoinColumn(name = "city_id", nullable = false)
  private City City;

  public Unit() {
    this.PostedDate = new Date(System.currentTimeMillis());
  }
}
