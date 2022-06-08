package util

import play.api.libs.json.{Json, Reads, Writes}
import resources.{UserRequest, UserResponse}

object JsonReads {
  implicit val usrRequestRead: Reads[UserRequest] = Json.reads[UserRequest]
}
