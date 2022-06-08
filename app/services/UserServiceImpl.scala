package services

import resources.Mappings.{RequestToUserMapping, UserToResponseMapping}
import cats.Monad
import cats.implicits._
import models.{DatabaseManager, UserDAO}
import resources.{DuplicateUser, NoModel, UserRequest, UserResponse}
import services.response.{ResponseMessage, ServiceResponse}
import slick.dbio.DBIO
import util.Conversions.{errorToServiceResponse, objectToServiceResponse}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class UserServiceImpl @Inject()(userRepo: UserDAO[DBIO],
                                                   dbManager: DatabaseManager[Future, DBIO]) extends UserService[Future] {

 override def findUserById(id: Long)(implicit ec: ExecutionContext): Future[ServiceResponse[UserResponse]] = {
    for {
      user <- dbManager.execute(userRepo.get(id))
    } yield user match {
      case Some(user) =>user.asResponse.as200
      case None => NoModel(id).as404
    }
  }

  override def add(request: UserRequest)(implicit ec: ExecutionContext): Future[ServiceResponse[UserResponse]] =
    for {
      user <- dbManager.tryExecute(
        userRepo.add(request.asUser)
      )
    } yield user match {
      case Success(value) => value.asResponse.as201
      case Failure(_) => DuplicateUser(request.email).as404
    }

  override def getAll: Future[ServiceResponse[Seq[UserResponse]]] = ???

  override def update(id: Long, request: UserRequest): Future[ServiceResponse[ResponseMessage]] = ???

  override def remove(id: Long): Future[ServiceResponse[ResponseMessage]] = ???
}
