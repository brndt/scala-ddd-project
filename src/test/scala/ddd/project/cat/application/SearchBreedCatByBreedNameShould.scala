package scala.ddd.project.cat.application

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.ddd.project.cat.domain.exception.CatBreedNotFoundException
import scala.ddd.project.cat.domain.{CatBreed, CatBreedDescription, CatBreedName}
import scala.ddd.project.cat.infrastructure.repository.CatBreedRepositoryMock
import scala.ddd.project.shared.infrastructure.UnitTestCase

class SearchBreedCatByBreedNameShould extends UnitTestCase with CatBreedRepositoryMock {
  private val search = new SearchBreedCatByBreedName(repository)

  "search a cat breed" in {

    val catBreed = CatBreed(CatBreedName("bengal"), CatBreedDescription("random description"))

    repositoryShouldFind(Some(catBreed), catBreed.name)

    search.search(catBreed.name.value).futureValue shouldBe catBreed
  }

  "search a non-existing cat breed" in {

    val catBreedName = CatBreedName("non_existing")

    repositoryShouldFind(None, catBreedName)

    search.search(catBreedName.value).failed.futureValue shouldBe a [CatBreedNotFoundException]
  }

}
