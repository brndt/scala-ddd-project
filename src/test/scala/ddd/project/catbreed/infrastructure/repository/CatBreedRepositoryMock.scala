package scala.ddd.project.catbreed.infrastructure.repository

import org.scalamock.scalatest.MockFactory

import scala.concurrent.Future
import scala.ddd.project.catbreed.domain.{CatBreed, CatBreedName, CatBreedRepository}

trait CatBreedRepositoryMock extends MockFactory {

  protected val repository: CatBreedRepository[Future] = mock[CatBreedRepository[Future]]

  protected def repositoryShouldFind(catBreedOption: Option[CatBreed], catBreedName: CatBreedName): Unit =
    (repository.searchCatsByBreed _)
      .expects(catBreedName)
      .returning(Future.successful(catBreedOption))
}