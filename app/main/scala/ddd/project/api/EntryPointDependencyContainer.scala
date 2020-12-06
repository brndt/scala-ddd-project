package main.scala.ddd.project.api

import main.scala.ddd.project.api.controller.GetCatByBreedController
import main.scala.ddd.project.cat.infrastructure.dependency_injection.CatDependencyContainer

final class EntryPointDependencyContainer(catDependencyContainer: CatDependencyContainer) {
  val getCatByBreedController = new GetCatByBreedController(catDependencyContainer.searchCatByBreed)
}