package main.scala.ddd.project.cat.infrastructure.dependency_injection

import akka.actor.ClassicActorSystemProvider
import main.scala.ddd.project.cat.application.SearchBreedCatByBreedName
import main.scala.ddd.project.cat.domain.CatBreedRepository
import main.scala.ddd.project.cat.infrastructure.repository.TheCatApiBreedRepository

import scala.concurrent.ExecutionContext

final class CatDependencyContainer(implicit executionContext: ExecutionContext, system: ClassicActorSystemProvider) {
  val theCatApiRepository: CatBreedRepository = new TheCatApiBreedRepository
  val searchCatByBreed: SearchBreedCatByBreedName = new SearchBreedCatByBreedName(theCatApiRepository)
}