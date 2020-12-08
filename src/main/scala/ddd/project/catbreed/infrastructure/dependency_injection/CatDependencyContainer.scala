package scala.ddd.project.catbreed.infrastructure.dependency_injection

import akka.actor.ClassicActorSystemProvider

import scala.ddd.project.catbreed.application.SearchBreedCatByBreedName
import scala.ddd.project.catbreed.domain.CatBreedRepository
import scala.ddd.project.catbreed.infrastructure.repository.TheCatApiBreedRepository
import scala.concurrent.ExecutionContext
import scala.ddd.project.cat.application.CreateCat
import scala.ddd.project.cat.domain.CatRepository
import scala.ddd.project.cat.infrastructure.repository.MysqlCatRepository

final class CatDependencyContainer(implicit executionContext: ExecutionContext, system: ClassicActorSystemProvider) {
  val theCatApiRepository: CatBreedRepository = new TheCatApiBreedRepository
  val mysqlCatRepository: CatRepository = new MysqlCatRepository

  val searchCatByBreed: SearchBreedCatByBreedName = new SearchBreedCatByBreedName(theCatApiRepository)
  val createCat: CreateCat = new CreateCat(mysqlCatRepository)
}