package scala.ddd.project.cat.application

import cats.implicits.catsStdInstancesForFuture

import scala.concurrent.ExecutionContext.Implicits.global
import scala.ddd.project.cat.domain.CatMother
import scala.ddd.project.cat.domain.exception.CatAlreadyExistsException
import scala.ddd.project.cat.infrastructure.repository.CatRepositoryMock
import scala.ddd.project.shared.infrastructure.UnitTestCase

final class CreateCatShould extends UnitTestCase with CatRepositoryMock {

  private val create = new CreateCat(repository)

  "create a cat" in {

    val cat = CatMother.random

    repositoryShouldFind(None, cat.catId)

    repositoryShouldSave(cat)

    create.create(cat.catId.value, cat.catName.value, cat.catAltNames.value, cat.catDateOfBirth.value.toString, cat.catCharacter.value, cat.catWeight.value, cat.catEnergyLevel.value).futureValue shouldBe()
  }

  "create a cat when he/she already exists" in {

    val cat = CatMother.random

    repositoryShouldFind(Some(cat), cat.catId)

    create.create(cat.catId.value, cat.catName.value, cat.catAltNames.value, cat.catDateOfBirth.value.toString, cat.catCharacter.value, cat.catWeight.value, cat.catEnergyLevel.value).failed.futureValue shouldBe a[CatAlreadyExistsException]
  }
}
