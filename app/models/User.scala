package models

case class User(
               id: Option[Long],
               email: String,
               password: String,
               firstName: String,
               lastName: String
               )
