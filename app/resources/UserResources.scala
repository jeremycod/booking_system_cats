package resources

final case class UserResponse(id: Long, email: String, firstName: String, lastName: String)
final case class UserRequest(email: String, password: String, firstName: String, lastName: String)