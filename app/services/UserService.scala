package services


import resources.{UserRequest, UserResponse}
import services.response.{ResponseMessage, ServiceResponse}

import javax.inject.Inject
import scala.concurrent.ExecutionContext
/*
DB[_]: Monad is known as a context bound and is just syntax sugar for implicit parameter lists:
class Service[DB[_]](implicit monad: Monad[DB[_])(repo:
  UserRepository[DB])
 */
trait UserService[F[_]] {
  def findUserById(id: Long)(implicit ec: ExecutionContext): F[ServiceResponse[UserResponse]]
  def add(request: UserRequest)(implicit ec: ExecutionContext): F[ServiceResponse[UserResponse]]
  def getAll: F[ServiceResponse[Seq[UserResponse]]]
  def update(id: Long, request: UserRequest): F[ServiceResponse[ResponseMessage]]
  def remove(id: Long): F[ServiceResponse[ResponseMessage]]
}
