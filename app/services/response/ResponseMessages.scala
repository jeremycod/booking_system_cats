package services.response

trait ResponseMessage {
  val message: String
}

final case class UserUpdated(id: Long) extends ResponseMessage {
  override val message = s"User with id $id updated"
}

final case class UserRemoved(id: Long) extends ResponseMessage {
  override val message = s"User with id $id removed"
}

