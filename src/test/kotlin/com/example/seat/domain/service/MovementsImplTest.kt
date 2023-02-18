package com.example.seat.domain.service

import com.example.seat.domain.exception.WrongInstructionException
import com.example.seat.domain.model.Instruction
import com.example.seat.mother.MowerLocationMother
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MovementsImplTest {

  private val sut = MovementsImpl()

  @Test
  fun `given a wrong instruction for spin should throw an exception`() {
    // given
    val mowerLocation = MowerLocationMother.simple()

    // then
    assertThrows<WrongInstructionException> {
      // when
      sut.spinNinetyDegrees(mowerLocation, Instruction.M)
    }

  }

}
