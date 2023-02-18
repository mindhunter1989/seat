package com.example.seat.domain.model

import com.example.seat.domain.exception.MowerOutOfPlateauLimitsException

data class MowerLocation(
  val plateauUpperRightCoordinates: Coordinates,
  val position: Coordinates,
  val heading: Heading,
) {

  init {
    if (position.xCoordinate < 0
      || position.yCoordinate < 0
      || position.xCoordinate > plateauUpperRightCoordinates.xCoordinate
      || position.yCoordinate > plateauUpperRightCoordinates.yCoordinate)
      throw MowerOutOfPlateauLimitsException(location = this)
  }

}
