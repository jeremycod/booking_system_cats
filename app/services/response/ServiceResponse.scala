package services.response

sealed trait ServiceResponse[+T]
sealed abstract class SuccessResponse[T](val result: T) extends ServiceResponse[T]
final case class OkResponse[T](override val result: T) extends SuccessResponse[T](result)
final case class CreatedResponse[T](override val result: T) extends SuccessResponse[T](result)

sealed abstract class ErrorResponse(val message: String) extends Exception with ServiceResponse[Nothing] {
  override def getMessage = message
}
final case class NotFoundResponse(override val message: String) extends ErrorResponse(message)