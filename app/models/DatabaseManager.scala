package models

import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.util.Try

trait DatabaseManager [F[_], DB[_]] {
  def execute[A](action: DB[A]): F[A]
  def executeTransactionally[A](action: DB[A]): F[A]
  def sequence[A](action: Seq[DB[A]]): DB[Seq[A]]
  def tryExecute[A](action: DB[A]): F[Try[A]]
}
