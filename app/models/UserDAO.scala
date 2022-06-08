package models

// https://medium.com/panaseer-labs-engineering-data-science/architecting-a-flexible-and-purely-functional-scala-back-end-using-slick-and-tagless-final-97b9754f5817
trait UserDAO[DB[_]] {
  def get(userId: Long): DB[Option[User]]
  def add(user: User): DB[User]
  def remove(userId: Long): DB[Int]
  def update(user: User): DB[Int]
}
