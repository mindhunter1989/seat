package com.example.seat.domain.service

import com.example.seat.domain.model.Heading
import com.example.seat.domain.model.Instruction
import com.example.seat.domain.Movements
import com.example.seat.domain.exception.WrongInstructionException
import com.example.seat.domain.model.MowerLocation
import org.springframework.stereotype.Service

@Service
class MovementsImpl : Movements {

  override fun spinNinetyDegrees(mowerLocation: MowerLocation, instruction: Instruction): MowerLocation =
    when (instruction) {
      Instruction.L -> spinToLeft(mowerLocation)
      Instruction.R -> spinToRight(mowerLocation)
      else -> throw WrongInstructionException(instruction)
    }

  override fun moveForward(mowerLocation: MowerLocation): MowerLocation =
    when (mowerLocation.heading) {
      Heading.N -> mowerLocation.copy(
        position = mowerLocation.position.copy(
          yCoordinate = mowerLocation.position.yCoordinate.plus(1)))
      Heading.E -> mowerLocation.copy(
        position = mowerLocation.position.copy(
          xCoordinate = mowerLocation.position.xCoordinate.plus(1)))
      Heading.S -> mowerLocation.copy(
        position = mowerLocation.position.copy(
          yCoordinate = mowerLocation.position.yCoordinate.minus(1)))
      Heading.W -> mowerLocation.copy(
        position = mowerLocation.position.copy(
          xCoordinate = mowerLocation.position.xCoordinate.minus(1)))
    }

  private fun spinToLeft(mowerLocation: MowerLocation): MowerLocation =
    when (mowerLocation.heading) {
      Heading.N -> changeHeadingTo(mowerLocation, Heading.W)
      Heading.W -> changeHeadingTo(mowerLocation, Heading.S)
      Heading.S -> changeHeadingTo(mowerLocation, Heading.E)
      Heading.E -> changeHeadingTo(mowerLocation, Heading.N)
    }

  private fun spinToRight(mowerLocation: MowerLocation): MowerLocation =
    when (mowerLocation.heading) {
      Heading.N -> changeHeadingTo(mowerLocation, Heading.E)
      Heading.E -> changeHeadingTo(mowerLocation, Heading.S)
      Heading.S -> changeHeadingTo(mowerLocation, Heading.W)
      Heading.W -> changeHeadingTo(mowerLocation, Heading.N)
    }

  private fun changeHeadingTo(mowerLocation: MowerLocation, heading: Heading) =
    mowerLocation.copy(heading = heading)

}
