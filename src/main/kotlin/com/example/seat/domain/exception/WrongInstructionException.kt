package com.example.seat.domain.exception

import com.example.seat.domain.model.Instruction

class WrongInstructionException(instruction: Instruction)
  : RuntimeException("Instruction ${instruction.name} not represent a spin")
