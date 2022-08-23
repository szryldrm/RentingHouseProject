package com.sezer.RentingHouse.controller;

import com.sezer.RentingHouse.entities.City;
import com.sezer.RentingHouse.entities.Unit;
import com.sezer.RentingHouse.services.impl.UnitServiceImpl;
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
public class UnitControllerIT {

  @Autowired private MockMvc mockMvc;
  @MockBean private UnitServiceImpl unitService;

  @Test
  @DisplayName("Test list all units called endpoint")
  public void should_list_all_units_when_get_method() throws Exception {

    var unit1 = new Unit();
    unit1.setId(UUID.randomUUID());
    unit1.setTitle("5- Example Renting House");

    var unit2 = new Unit();
    unit2.setId(UUID.randomUUID());
    unit2.setTitle("6- Example Renting House");

    List<Unit> allUnits = List.of(unit1, unit2);

    Mockito.when(unitService.getAll()).thenReturn(allUnits);

    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/units").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].title", is("5- Example Renting House")));
  }

  @Test
  @DisplayName("Test return an unit by id")
  public void should_return_unit_by_id_when_get_method() throws Exception {

    var unit1 = new Unit();
    unit1.setId(UUID.randomUUID());
    unit1.setTitle("8- Example Renting House");

    given(unitService.get(unit1.getId())).willReturn(unit1);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/units/" + unit1.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("title", is(unit1.getTitle())));
  }

  @Test
  @DisplayName("Test create a new Unit")
  public void should_return_created_unit_when_post_method() throws Exception {

    var unit1 = new Unit();
    unit1.setId(UUID.randomUUID());
    unit1.setTitle("5- Example Renting House");
    unit1.setDescription("5- Example Renting House");
    unit1.setNumberOfBalcony(1);
    unit1.setNumberOfBathroom(3);
    unit1.setNumberOfBedroom(2);
    unit1.setArea(100);
    unit1.setPostalCode("07545");
    unit1.setAddress("Greizer Strasse 10");
    unit1.setTel("+4917620559173");
    unit1.setEmail("sezer.yildirimmmm@live.com");
    unit1.setResponsible("Sezer Yildirim");
    unit1.setPrice(500);

    var city = new City();
    city.setId(UUID.randomUUID());
    city.setName("Mannheim");

    unit1.setCity(city);

    given(unitService.save(unit1)).willReturn(unit1);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(unit1)))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Test return bad request while creating a new unit")
  public void should_return_bad_request_unit_when_post_method() throws Exception {

    var unit1 = new Unit();
    unit1.setId(UUID.randomUUID());
    unit1.setTitle("5- Example Renting House");
    unit1.setDescription("5- Example Renting House");

    var city = new City();
    city.setId(UUID.randomUUID());
    city.setName("Mannheim");

    unit1.setCity(city);

    given(unitService.save(unit1)).willReturn(unit1);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/units")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(unit1)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Test return HttpCode:204 while deleting a new unit")
  public void should_return_success_when_delete_method() throws Exception {
    var unit1 = new Unit();
    unit1.setId(UUID.randomUUID());
    unit1.setTitle("5- Example Renting House");
    unit1.setDescription("5- Example Renting House");

    doNothing().when(unitService).deleteById(unit1.getId());

    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/api/units/" + unit1.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}
