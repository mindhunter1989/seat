package com.example.seat.infrastructure.rest.response

import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import kotlinx.serialization.Serializable

@Serializable
data class MowerFinalLocation(
  val xCoordinate: Int,
  val yCoordinate: Int,
  val heading: String,
) {

  companion object {
    fun from(finalPosition: Coordinates, finalHeading: Heading) =
      MowerFinalLocation(
        xCoordinate = finalPosition.xCoordinate,
        yCoordinate = finalPosition.yCoordinate,
        heading = finalHeading.name,
      )
  }

}
