package resources

import models.User

object Mappings {

  implicit class UserToResponseMapping(user: User) {
    def asResponse = UserResponse(user.id.getOrElse(0), user.email, user.firstName, user.lastName)
  }
  implicit class RequestToUserMapping(request: UserRequest) {
    def asUser = User(None, request.email, request.password, request.firstName, request.lastName)
  }

}
