package com.example.seat.domain.port.incoming

import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import com.example.seat.domain.port.incoming.`in`.ControlMowerRequest

interface ControlMowerPort {

  operator fun invoke(request: ControlMowerRequest) : Result

  sealed class Result(
    open val lastMowerPosition: Coordinates,
    open val lastMowerHeading: Heading,
  ) {

    data class Ok(
      override val lastMowerPosition: Coordinates,
      override val lastMowerHeading: Heading,
    ) : Result(
      lastMowerPosition = lastMowerPosition,
      lastMowerHeading = lastMowerHeading,
    )

    data class Error(
      override val lastMowerPosition: Coordinates,
      override val lastMowerHeading: Heading,
      val message: String,
    ) : Result(
      lastMowerPosition = lastMowerPosition,
      lastMowerHeading = lastMowerHeading,
    )

  }

}
