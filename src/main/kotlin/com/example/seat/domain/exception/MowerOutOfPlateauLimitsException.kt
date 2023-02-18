package com.example.seat.domain.exception

import com.example.seat.domain.model.MowerLocation

class MowerOutOfPlateauLimitsException(val location: MowerLocation)
  : RuntimeException("Mower position is off-limits")
