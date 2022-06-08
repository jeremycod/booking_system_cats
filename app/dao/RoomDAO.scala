package dao

import models.Room
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RoomDAO @Inject()(
      protected val dbConfigProvider: DatabaseConfigProvider
  )(
  implicit executionContext: ExecutionContext
  ) extends HasDatabaseConfigProvider[JdbcProfile]{
    import profile.api._

    private val Rooms = TableQuery[RoomsTable]
    def all(): Future[Seq[Room]] = db.run(Rooms.result)

    private class RoomsTable(tag: Tag) extends Table[Room](tag, "rooms"){
      def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
      def price = column[BigDecimal]("price")
      def capacity = column[Int]("capacity")

      override def * = (id, price, capacity) <> (Room.tupled, Room.unapply)
    }
  }

