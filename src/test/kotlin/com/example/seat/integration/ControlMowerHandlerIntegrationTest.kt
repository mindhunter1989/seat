package com.example.seat.integration

import com.example.seat.domain.Movements
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ControlMowerHandlerIntegrationTest {

  @Autowired
  private lateinit var mockMvc: MockMvc

  @Autowired
  private lateinit var movements: Movements

  @Test
  fun `given a location 1 2 N and instructions LMLMLMLMM when action return the new mower location should return 200 OK response`() {
    // when
    mockMvc.perform(
      MockMvcRequestBuilders.get("/mower/control")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""{ "plateauUpperRightCoordinates": "5 5", "mowerDeployment": { "mowerLocation": "1 2 N", 
          |"mowerInstructions": "LMLMLMLMM" } }""".trimMargin())
        .accept(MediaType.APPLICATION_JSON))
      // then
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.xCoordinate").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.yCoordinate").value(3))
      .andExpect(MockMvcResultMatchers.jsonPath("$.heading").value("N"))
  }

  @Test
  fun `given a location 3 3 E and instructions MMRMMRMRRM when action return the new mower location should return 200 OK response`() {
    // when
    mockMvc.perform(
      MockMvcRequestBuilders.get("/mower/control")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""{ "plateauUpperRightCoordinates": "5 5", "mowerDeployment": { "mowerLocation": "3 3 E", 
        |"mowerInstructions": "MMRMMRMRRM" } }""".trimMargin())
        .accept(MediaType.APPLICATION_JSON))
      // then
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.xCoordinate").value(5))
      .andExpect(MockMvcResultMatchers.jsonPath("$.yCoordinate").value(1))
      .andExpect(MockMvcResultMatchers.jsonPath("$.heading").value("E"))
  }

}
