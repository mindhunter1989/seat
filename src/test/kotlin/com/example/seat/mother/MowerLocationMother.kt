package com.example.seat.mother

import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import com.example.seat.domain.model.MowerLocation

object MowerLocationMother {

  fun simple() =
    MowerLocation(
      plateauUpperRightCoordinates = Coordinates(xCoordinate = 5, yCoordinate = 5),
      position = Coordinates(xCoordinate = 1, yCoordinate = 3),
      heading = Heading.N,
    )

  fun badLocation() =
    MowerLocation(
      plateauUpperRightCoordinates = Coordinates(xCoordinate = 1, yCoordinate = 1),
      position = Coordinates(xCoordinate = 2, yCoordinate = 2),
      heading = Heading.N,
    )

}
