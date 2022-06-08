package controllers

import dao.UserDAOImpl
import play.api.libs.json.Json
import resources.UserRequest
import util.JsonWrites._
import services.{UserService, UserServiceImpl}
import util.JsonReads.usrRequestRead

import javax.inject.Inject
import scala.concurrent.Future

// TODO: clean these up.
// SEE:  https://github.com/playframework/play-slick/blob/master/samples/basic/app/controllers/Application.scala

import play.api.mvc.{ AbstractController, ControllerComponents }

import scala.concurrent.ExecutionContext


class UserController @Inject()(
                                userDao: UserDAOImpl,
                                userService: UserService[Future],
                                controllerComponents: ControllerComponents
                                 )(
                                   implicit executionContext: ExecutionContext
                                 ) extends AbstractController(controllerComponents) {


  // TODO find a better way to do this
  def findById(id: Long) = Action.async { implicit request =>
    for {
      response <- userService.findUserById(id)
    } yield Ok(Json.toJson(response))

  }

  def addUser = Action.async { implicit request =>
    val user = request.body.asJson.get.as[UserRequest]

    for {
      response <- userService.add(user)
    } yield Ok(Json.toJson(response))
  }

}
