package models

import java.time.LocalDate

case class Reservation(
                      id: Long,
                      startDate: LocalDate,
                      endDate: LocalDate,
                      userId: Long,
                      roomId: Long,
                      totalPrice: BigDecimal
                      )
