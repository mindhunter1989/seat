package com.example.seat.action

import com.example.seat.domain.Movements
import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import com.example.seat.domain.service.MovementsImpl
import com.example.seat.mother.ControlMowerPortMother
import com.example.seat.mother.ControlMowerRequestMother
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ControlMowerTest {

  private lateinit var movements: Movements
  private lateinit var sut: ControlMower

  @BeforeEach
  fun setUp() {
    movements = MovementsImpl()

    sut = ControlMower(
      movements = movements,
    )
  }

  @Test
  fun `given a location and a list of instructions should return the new mower location`() {
    // given
    val request = ControlMowerRequestMother.simple()

    // when
    val response = sut(request)

    // then
    Assertions.assertEquals(
      ControlMowerPortMother.ok(),
      response
    )
  }

  @Test
  fun `given a bad initial location should return an error`() {
    // given
    val request = ControlMowerRequestMother.badInitialLocation()

    // when
    val response = sut(request)

    // then
    Assertions.assertEquals(
      ControlMowerPortMother.error(),
      response
    )
  }

  @Test
  fun `given a bad set of instructions should return an error`() {
    // given
    val request = ControlMowerRequestMother.badLocationDueToInstruction()

    // when
    val response = sut(request)

    // then
    Assertions.assertEquals(
      ControlMowerPortMother.error(
        lastMowerPosition = Coordinates(xCoordinate = 4, yCoordinate = 3),
        lastMowerHeading = Heading.E
      ),
      response
    )
  }

}
