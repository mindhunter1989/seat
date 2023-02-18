package com.example.seat.infrastructure.rest

import kotlinx.serialization.Serializable

@Serializable
data class MowerDeployment(
  val mowerLocation: String,
  val mowerInstructions: String,
)
