package com.example.seat.domain.port.incoming.`in`

import com.example.seat.domain.model.Instruction
import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading

data class ControlMowerRequest(
  val plateauUpperRightCoordinates: Coordinates,
  val mowerPosition: Coordinates,
  val mowerHeading: Heading,
  val mowerInstructions: List<Instruction>
)
