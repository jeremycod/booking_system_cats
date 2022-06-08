package dao

import models.Reservation
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReservationDAO @Inject()(
                                protected val dbConfigProvider: DatabaseConfigProvider
                              )(
                                implicit executionContext: ExecutionContext
                              ) extends HasDatabaseConfigProvider[JdbcProfile]{
  import profile.api._

  private val Reservations = TableQuery[ReservationsTable]
  def all(): Future[Seq[Reservation]] = db.run(Reservations.result)

  private class ReservationsTable(tag: Tag) extends Table[Reservation](tag, "reservations"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def startDate = column[LocalDate]("start_date")
    def endDate = column[LocalDate]("end_date")
    def userId = column[Long]("user_id")
    def roomId = column[Long]("room_id")
    def totalPrice = column[BigDecimal]("total_price")
    override def * = (id, startDate, endDate, userId, roomId, totalPrice) <> (Reservation.tupled, Reservation.unapply)
  }
}

