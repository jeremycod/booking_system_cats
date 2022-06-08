package dao

import models.DatabaseManager
import play.api.db.slick.DatabaseConfigProvider
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.Future
import scala.util.Try

class SlickDatabaseManager @Inject() (dbConfigProvider: DatabaseConfigProvider) extends DatabaseManager[Future, DBIO] {

  protected val db = dbConfigProvider.get[JdbcProfile].db

  override def execute[A](action: DBIO[A]): Future[A] = db.run(action)

  override def executeTransactionally[A](action: DBIO[A]): Future[A] = db.run(action.transactionally)

  override def sequence[A](action: Seq[DBIO[A]]): DBIO[Seq[A]] = DBIO.sequence(action)

  override def tryExecute[A](action: DBIO[A]): Future[Try[A]] = db.run(action.asTry)
}
