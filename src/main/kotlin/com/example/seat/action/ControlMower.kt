package com.example.seat.action

import com.example.seat.domain.model.Instruction
import com.example.seat.domain.Movements
import com.example.seat.domain.exception.MowerOutOfPlateauLimitsException
import com.example.seat.domain.model.MowerLocation
import com.example.seat.domain.port.incoming.ControlMowerPort
import com.example.seat.domain.port.incoming.`in`.ControlMowerRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ControlMower(
  @Autowired private val movements: Movements,
) : ControlMowerPort {

  override fun invoke(request: ControlMowerRequest): ControlMowerPort.Result =
    try {
      request.mowerPosition
        .let { position ->
          var temporalLocation = MowerLocation(
            plateauUpperRightCoordinates = request.plateauUpperRightCoordinates,
            position = position,
            heading = request.mowerHeading,
          )
          request.mowerInstructions.forEach { instruction ->
            temporalLocation = when (instruction) {
              Instruction.L, Instruction.R -> movements.spinNinetyDegrees(temporalLocation, instruction)
              Instruction.M -> movements.moveForward(temporalLocation)
            }
          }
            .let {
              ControlMowerPort.Result.Ok(
                lastMowerPosition = temporalLocation.position, lastMowerHeading = temporalLocation.heading)
            }
        }
    } catch (e: MowerOutOfPlateauLimitsException) {
      ControlMowerPort.Result.Error(
        lastMowerPosition = e.location.position,
        lastMowerHeading = e.location.heading,
        message = e.message!!
      )
    }

}
