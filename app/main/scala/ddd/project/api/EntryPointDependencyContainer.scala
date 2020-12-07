package scala.ddd.project.api

import scala.ddd.project.api.controller.GetCatBreedByBreedNameController
import scala.ddd.project.cat.infrastructure.dependency_injection.CatDependencyContainer

final class EntryPointDependencyContainer(catDependencyContainer: CatDependencyContainer) {
  val getCatByBreedController = new GetCatBreedByBreedNameController(catDependencyContainer.searchCatByBreed)
}