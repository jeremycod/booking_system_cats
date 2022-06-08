package dao

import models.{DataContext, User, UserDAO}
import play.api.db.slick.DatabaseConfigProvider
import slick.dbio.{DBIO => SLICKDBIO}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class UserDAOImpl @Inject()(
                             protected val dbConfigProvider: DatabaseConfigProvider,
                             val context: DataContext
                           )(
                             implicit executionContext: ExecutionContext
                           ) extends UserDAO[SLICKDBIO] {

  import context.profile.api._

  override def get(userId: Long): SLICKDBIO[Option[User]] = context.Users.filter(_.id === userId).result.headOption

  override def add(user: User): SLICKDBIO[User] = (context.Users returning context.Users.map(_.id) into ((user, id) =>
    user.copy(id = Some(id))
  )) += user

  override def remove(userId: Long): SLICKDBIO[Int] = context.Users.filter(_.id === userId).delete

  override def update(user: User): SLICKDBIO[Int] =
    context.Users.filter(_.id === user.id).update(user)
}
