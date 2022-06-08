package util

import resources.ErrorMessage
import services.response.{CreatedResponse, NotFoundResponse, OkResponse}

object Conversions {
  /** Object conversion to the successfull service response with payload.
   * @param obj
   *   Service method result instance.
   */
  implicit class objectToServiceResponse[A](obj: A) {
    def as200 = OkResponse(obj)
    def as201 = CreatedResponse(obj)
  }
  implicit class errorToServiceResponse(msg: ErrorMessage) {
    def as404 = NotFoundResponse(msg.getMessage)
  }
}
