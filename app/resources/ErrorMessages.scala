package resources

sealed abstract class ErrorMessage extends Exception
final case class NoModel(id: Long) extends ErrorMessage {
  override def getMessage = "Model with id $id not found"
}
final case class DuplicateUser(email: String) extends ErrorMessage {
  override def getMessage = "User with email $email already exists"
}
