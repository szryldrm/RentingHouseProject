package com.sezer.RentingHouse.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class City {

  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", insertable = false, updatable = false, nullable = false)
  private UUID Id;

  @NotBlank(message = "City Name is Mandatory")
  @Column(name = "name")
  private String Name;

  @OneToMany(mappedBy = "City")
  @JsonIgnore
  private List<Unit> Units;
}
