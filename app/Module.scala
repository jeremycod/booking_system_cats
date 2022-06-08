import com.google.inject.{AbstractModule, TypeLiteral}
import dao.{SlickDatabaseManager, UserDAOImpl}
import models.{DatabaseManager, UserDAO}
import services.response.ServiceResponse

import java.time.Clock
import services.{ApplicationTimer, AtomicCounter, Counter, UserService, UserServiceImpl}
import slick.dbio.DBIO
import slick.jdbc.hikaricp.HikariCPJdbcDataSource
import slick.jdbc.{JdbcProfile, PostgresProfile}


import scala.concurrent.Future

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure() = {
    // Use the system clock as the default implementation of Clock
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    // Ask Guice to create an instance of ApplicationTimer when the
    // application starts.
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    // Set AtomicCounter as the implementation for Counter.
    bind(classOf[Counter]).to(classOf[AtomicCounter])
    bind(new TypeLiteral[UserDAO[DBIO]](){}).to(classOf[UserDAOImpl])
    bind(new TypeLiteral[UserService[Future]](){}).to(classOf[UserServiceImpl])
    bind(new TypeLiteral[DatabaseManager[Future, DBIO]](){}).to(classOf[SlickDatabaseManager])
  }

}
