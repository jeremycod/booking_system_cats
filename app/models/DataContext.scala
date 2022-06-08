package models

import slick.jdbc.JdbcProfile
import slick.lifted
import slick.jdbc.meta.MTable

import javax.inject.Inject

class DataContext @Inject()() {
val profile = util.PgProfile
import profile.api._



  final class UsersTable(tag: Tag) extends Table[User](tag, "users"){
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("email")
    def password = column[String]("password")
    def firstName = column[String]("first_name")
    def lastName = column[String]("last_name")
    override def * = (id.?, email, password, firstName, lastName) <> (User.tupled, User.unapply)
  }

  val Users = TableQuery[UsersTable]
}
