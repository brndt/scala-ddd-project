package main.scala.ddd.project.api

import main.scala.ddd.project.api.controller.GetCatBreedByBreedNameController
import main.scala.ddd.project.cat.infrastructure.dependency_injection.CatDependencyContainer

final class EntryPointDependencyContainer(catDependencyContainer: CatDependencyContainer) {
  val getCatByBreedController = new GetCatBreedByBreedNameController(catDependencyContainer.searchCatByBreed)
}