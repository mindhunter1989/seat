package com.example.seat.infrastructure.rest.handler

import com.example.seat.action.ControlMower
import com.example.seat.domain.model.Coordinates
import com.example.seat.domain.model.Heading
import com.example.seat.domain.model.Instruction
import com.example.seat.domain.port.incoming.ControlMowerPort
import com.example.seat.domain.port.incoming.`in`.ControlMowerRequest
import com.example.seat.infrastructure.rest.exception.BadRequestException
import com.example.seat.infrastructure.rest.exception.DomainException
import com.example.seat.infrastructure.rest.input.ControlMowerHandlerInput
import com.example.seat.infrastructure.rest.response.ErrorResponse
import com.example.seat.infrastructure.rest.response.MowerFinalLocation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mower")
class ControlMowerHandler(
  @Autowired private val controlMower: ControlMower,
  ) {

  @GetMapping("/control")
  fun handleRequest(@RequestBody body: ControlMowerHandlerInput): MowerFinalLocation =
    controlMower(body.toControlMowerRequest())
      .let { finalLocation ->
        when (finalLocation) {
          is ControlMowerPort.Result.Ok -> MowerFinalLocation.from(
            finalPosition = finalLocation.lastMowerPosition, finalHeading = finalLocation.lastMowerHeading
          )
          is ControlMowerPort.Result.Error -> throw DomainException(finalLocation.message)
        }
      }

  private fun ControlMowerHandlerInput.toControlMowerRequest(): ControlMowerRequest =
    try {
      ControlMowerRequest(
        plateauUpperRightCoordinates = Coordinates(
          xCoordinate = this.plateauUpperRightCoordinates.split(" ").first().toInt(),
          yCoordinate = this.plateauUpperRightCoordinates.split(" ")[1].toInt(),
        ),
        mowerPosition = Coordinates(
          xCoordinate = this.mowerDeployment.mowerLocation.split(" ").first().toInt(),
          yCoordinate = this.mowerDeployment.mowerLocation.split(" ")[1].toInt(),
        ),
        mowerHeading = Heading.valueOf(this.mowerDeployment.mowerLocation.split(" ")[2]),
        mowerInstructions = this.mowerDeployment.mowerInstructions.toCharArray()
          .map { instruction -> Instruction.valueOf(instruction.toString()) },
      )
    } catch (e: Exception) {
      throw BadRequestException()
    }

  @ExceptionHandler(BadRequestException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleBadRequestException(e: BadRequestException): ErrorResponse = formatError(e, HttpStatus.BAD_REQUEST)

  @ExceptionHandler(DomainException::class)
  @ResponseStatus(HttpStatus.CONFLICT)
  fun handleDomainException(e: DomainException): ErrorResponse = formatError(e, HttpStatus.CONFLICT)

  private fun formatError(e: RuntimeException, status: HttpStatus) =
    ErrorResponse(
      httpErrorCode = status.value(),
      message = e.message,
    )

}
