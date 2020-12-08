package scala.ddd.project.cat.infrastructure.repository

import org.scalamock.scalatest.MockFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.ddd.project.cat.domain.{CatBreed, CatBreedName, CatBreedRepository}

trait CatBreedRepositoryMock extends MockFactory {

  protected val repository: CatBreedRepository = mock[CatBreedRepository]

  protected def repositoryShouldFind(catBreedOption: Option[CatBreed], catBreedName: CatBreedName): Unit =
    (repository.searchCatsByBreed _)
      .expects(catBreedName)
      .returning(Future.successful(catBreedOption))
}