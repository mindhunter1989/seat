package com.example.seat.mother

import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import com.example.seat.domain.port.incoming.ControlMowerPort

object ControlMowerPortMother {

  fun ok() =
    ControlMowerPort.Result.Ok(
      lastMowerPosition = Coordinates(xCoordinate = 1, yCoordinate = 3),
      lastMowerHeading = Heading.N
    )

  fun error(
    lastMowerPosition: Coordinates = Coordinates(xCoordinate = 2, yCoordinate = 2),
    lastMowerHeading: Heading = Heading.N
  ) =
    ControlMowerPort.Result.Error(
      lastMowerPosition = lastMowerPosition,
      lastMowerHeading = lastMowerHeading,
      message = "Mower position is off-limits",
    )

}
