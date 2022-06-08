package models

object DResult {
  case class Fail(message: String, cause: Option[Throwable] = None)
  type DResult[A] = Either[Fail, A]

}
