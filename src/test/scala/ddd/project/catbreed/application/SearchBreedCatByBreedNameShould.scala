package scala.ddd.project.catbreed.application

import cats.implicits.catsStdInstancesForFuture

import scala.concurrent.ExecutionContext.Implicits.global
import scala.ddd.project.catbreed.domain.exception.CatBreedNotFoundException
import scala.ddd.project.catbreed.domain.{CatBreedMother, CatBreedNameMother}
import scala.ddd.project.catbreed.infrastructure.repository.CatBreedRepositoryMock
import scala.ddd.project.shared.infrastructure.UnitTestCase

final class SearchBreedCatByBreedNameShould extends UnitTestCase with CatBreedRepositoryMock {
  private val search = new SearchBreedCatByBreedName(repository)

  "search a cat breed" in {

    val catBreed = CatBreedMother.random

    repositoryShouldFind(Some(catBreed), catBreed.name)

    search.search(catBreed.name.value).futureValue shouldBe catBreed
  }

  "search a non-existing cat breed" in {

    val catBreedName = CatBreedNameMother.random

    repositoryShouldFind(None, catBreedName)

    search.search(catBreedName.value).failed.futureValue shouldBe a[CatBreedNotFoundException]
  }

}
