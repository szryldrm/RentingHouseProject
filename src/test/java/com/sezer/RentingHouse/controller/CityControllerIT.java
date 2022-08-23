package com.sezer.RentingHouse.controller;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.services.impl.CityServiceImpl;
import com.sezer.RentingHouse.utilities.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerIT {

  @Autowired private MockMvc mockMvc;
  @MockBean private CityServiceImpl cityService;

  @Test
  @DisplayName("Test list all cities called endpoint")
  public void should_list_all_cities_when_get_method() throws Exception {

    var city1 = new City();
    city1.setId(UUID.randomUUID());
    city1.setName("Hamburg");

    var city2 = new City();
    city2.setId(UUID.randomUUID());
    city2.setName("Stuttgart");

    var allCities = List.of(city1, city2);

    Mockito.when(cityService.getAll()).thenReturn(allCities);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/cities").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("Hamburg")));
  }

  @Test
  @DisplayName("Test return an city by id")
  public void should_return_city_by_id_when_get_method() throws Exception {

    var city = new City();
    city.setId(UUID.randomUUID());
    city.setName("Stuttgart");

    given(cityService.get(city.getId())).willReturn(city);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/cities/" + city.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", is(city.getName())));
  }

  @Test
  @DisplayName("Test create a new city")
  public void should_return_created_city_when_post_method() throws Exception {

    var city1 = new City();
    city1.setId(UUID.randomUUID());
    city1.setName("Stuttgart");

    given(cityService.save(city1)).willReturn(city1);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(city1)))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Test return bad request while creating a new city")
  public void should_return_bad_request_city_when_post_method() throws Exception {

    var city = new City();
    city.setId(UUID.randomUUID());

    given(cityService.save(city)).willReturn(city);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(city)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Test return HttpCode:204 while deleting a new unit")
  public void should_return_success_when_delete_method() throws Exception {
    var city = new City();
    city.setId(UUID.randomUUID());
    city.setName("Stuttgart");

    doNothing().when(cityService).deleteById(city.getId());

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/api/cities/" + city.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}
