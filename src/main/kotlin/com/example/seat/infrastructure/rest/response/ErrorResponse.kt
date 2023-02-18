package com.example.seat.infrastructure.rest.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
  private val httpErrorCode: Int,
  private val message: String?,
)
