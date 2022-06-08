package util

import play.api.libs.json._
import play.api.libs.functional.syntax._
import resources.UserResponse
import services.response.{CreatedResponse, ErrorResponse, NotFoundResponse, ServiceResponse, SuccessResponse}

object JsonWrites {

  implicit val usrResponseWrite: Writes[UserResponse] = Json.writes[UserResponse]
  implicit def serviceResponseWrites[UserResponse]: Writes[ServiceResponse[UserResponse]] = Json.writes[ServiceResponse[UserResponse]]
  implicit val serviceResponseBaseWrite: Writes[ServiceResponse[UserResponse]] = new Writes[ServiceResponse[UserResponse]] {
    override def writes(o: ServiceResponse[UserResponse]): JsValue = o match {
      case sr: CreatedResponse[UserResponse] => Json.toJson(sr)

    }
  }
  implicit val createdResponseWrite: Writes[CreatedResponse[UserResponse]] = Json.writes[CreatedResponse[UserResponse]]
  implicit val errorResponseWrite: Writes[ErrorResponse] = Json.writes[ErrorResponse]
  implicit val notFoundResponseWrite: Writes[NotFoundResponse] = Json.writes[NotFoundResponse]
}
