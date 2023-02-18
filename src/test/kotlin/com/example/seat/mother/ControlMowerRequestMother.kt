package com.example.seat.mother

import com.example.seat.domain.model.Instruction
import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import com.example.seat.domain.port.incoming.`in`.ControlMowerRequest

object ControlMowerRequestMother {

  fun simple() =
    ControlMowerRequest(
      plateauUpperRightCoordinates = Coordinates(xCoordinate = 5, yCoordinate = 5),
      mowerPosition = Coordinates(xCoordinate = 1, yCoordinate = 2),
      mowerHeading = Heading.N,
      mowerInstructions = listOf(
        Instruction.L, Instruction.M, Instruction.L, Instruction.M, Instruction.L,
        Instruction.M, Instruction.L, Instruction.M, Instruction.M),
    )

  fun badInitialLocation() =
    ControlMowerRequest(
      plateauUpperRightCoordinates = Coordinates(xCoordinate = 1, yCoordinate = 1),
      mowerPosition = Coordinates(xCoordinate = 2, yCoordinate = 2),
      mowerHeading = Heading.N,
      mowerInstructions = listOf(Instruction.L),
    )

  fun badLocationDueToInstruction() =
    ControlMowerRequest(
      plateauUpperRightCoordinates = Coordinates(xCoordinate = 3, yCoordinate = 3),
      mowerPosition = Coordinates(xCoordinate = 3, yCoordinate = 3),
      mowerHeading = Heading.N,
      mowerInstructions = listOf(Instruction.R, Instruction.M),
    )

}
