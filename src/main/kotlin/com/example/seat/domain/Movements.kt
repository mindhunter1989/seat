package com.example.seat.domain

import com.example.seat.domain.model.Instruction
import com.example.seat.domain.model.MowerLocation

interface Movements {
  fun spinNinetyDegrees(mowerLocation: MowerLocation, instruction: Instruction): MowerLocation
  fun moveForward(mowerLocation: MowerLocation): MowerLocation
}
