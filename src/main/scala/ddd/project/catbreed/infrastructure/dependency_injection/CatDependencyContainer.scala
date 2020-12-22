package scala.ddd.project.catbreed.infrastructure.dependency_injection

import akka.actor.ClassicActorSystemProvider
import cats.implicits.catsStdInstancesForFuture
import scalikejdbc.AutoSession
import scalikejdbc.config.DBsWithEnv

import scala.concurrent.{ExecutionContext, Future}
import scala.ddd.project.cat.application.CreateCat
import scala.ddd.project.cat.domain.CatRepository
import scala.ddd.project.cat.infrastructure.repository.MysqlCatRepository
import scala.ddd.project.catbreed.application.SearchBreedCatByBreedName
import scala.ddd.project.catbreed.domain.CatBreedRepository
import scala.ddd.project.catbreed.infrastructure.repository.TheCatApiBreedRepository

final class CatDependencyContainer(environment: String)(implicit executionContext: ExecutionContext, system: ClassicActorSystemProvider) {

  DBsWithEnv(environment).setupAll
  implicit val DBSession: AutoSession.type = AutoSession

  val theCatApiRepository: CatBreedRepository[Future] = new TheCatApiBreedRepository
  val mysqlCatRepository: CatRepository[Future] = new MysqlCatRepository

  val searchCatByBreed: SearchBreedCatByBreedName[Future] = new SearchBreedCatByBreedName(theCatApiRepository)
  val createCat: CreateCat[Future] = new CreateCat(mysqlCatRepository)
}