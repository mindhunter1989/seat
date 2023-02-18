package com.example.seat.domain.model

import com.example.seat.domain.exception.MowerOutOfPlateauLimitsException
import com.example.seat.mother.MowerLocationMother
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MowerLocationTest {

  @Test
  fun `can not create a mower location with an invalid position`() {
    // then
    val exception = assertThrows<MowerOutOfPlateauLimitsException> {
      // given and when
      MowerLocationMother.badLocation()
    }

    Assertions.assertEquals(
      "Mower position is off-limits",
      exception.message
    )
  }

}
