package com.example.seat.infrastructure.rest.handler

import com.example.seat.action.ControlMower
import com.example.seat.mother.ControlMowerPortMother
import com.example.seat.mother.ControlMowerRequestMother
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(ControlMowerHandler::class)
internal class ControlMowerHandlerTest {

  @Autowired
  private lateinit var mockMvc: MockMvc

  @MockBean
  private lateinit var action: ControlMower

  @Test
  fun `given a location and a list of instructions when action return the new mower location should return 200 OK response`() {
    // given
    whenever(action(ControlMowerRequestMother.simple())).thenReturn(ControlMowerPortMother.ok())

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
  fun `given a bad initial location when action return an error should return 409 CONFLICT response`() {
    // given
    whenever(action(ControlMowerRequestMother.badInitialLocation()))
      .thenReturn(ControlMowerPortMother.error())

    // when
    mockMvc.perform(
      MockMvcRequestBuilders.get("/mower/control")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""{ "plateauUpperRightCoordinates": "1 1", "mowerDeployment": { "mowerLocation": "2 2 N", 
          |"mowerInstructions": "L" } }""".trimMargin())
        .accept(MediaType.APPLICATION_JSON))
      // then
      .andExpect(MockMvcResultMatchers.status().isConflict)
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.httpErrorCode").value(409))
      .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Mower position is off-limits"))
  }

  @Test
  fun `given an incorrect entry should return 400 BAD_REQUEST response`() {
    // when
    mockMvc.perform(
      MockMvcRequestBuilders.get("/mower/control")
        // given
        .contentType(MediaType.APPLICATION_JSON)
        .content("""{ "plateauUpperRightCoordinates": "XXX", "mowerDeployment": { "mowerLocation": "2 2 N", 
          |"mowerInstructions": "L" } }""".trimMargin())
        .accept(MediaType.APPLICATION_JSON))
      // then
      .andExpect(MockMvcResultMatchers.status().isBadRequest)
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.jsonPath("$.httpErrorCode").value(400))
      .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Some data has an incorrect value"))
  }

}
