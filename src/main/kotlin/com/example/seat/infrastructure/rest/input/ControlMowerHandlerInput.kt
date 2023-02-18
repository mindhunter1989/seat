package com.example.seat.infrastructure.rest.input

import com.example.seat.infrastructure.rest.MowerDeployment
import kotlinx.serialization.Serializable

@Serializable
data class ControlMowerHandlerInput(
  val plateauUpperRightCoordinates: String,
  val mowerDeployment: MowerDeployment,
)
