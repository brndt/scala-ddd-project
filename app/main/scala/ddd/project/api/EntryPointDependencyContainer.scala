package scala.ddd.project.api

import scala.ddd.project.api.controller.{GetCatBreedByBreedNameController, PostCatController}
import scala.ddd.project.catbreed.infrastructure.dependency_injection.CatDependencyContainer

final class EntryPointDependencyContainer(catDependencyContainer: CatDependencyContainer) {
  val getCatByBreedController = new GetCatBreedByBreedNameController(catDependencyContainer.searchCatByBreed)
  val postCatController = new PostCatController(catDependencyContainer.createCat)
}