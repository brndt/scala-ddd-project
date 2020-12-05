package main.scala.ddd.project.cat.infrastructure.dependency_injection

import main.scala.ddd.project.cat.application.SearchCatByBreed
import main.scala.ddd.project.cat.domain.CatRepository
import main.scala.ddd.project.cat.infrastructure.repository.TheCatApiRepository

import scala.concurrent.ExecutionContext

final class CatDependencyContainer(implicit executionContext: ExecutionContext)  {
  val theCatApiRepository: CatRepository = new TheCatApiRepository
  val searchCatByBreed: SearchCatByBreed = new SearchCatByBreed(theCatApiRepository)
}