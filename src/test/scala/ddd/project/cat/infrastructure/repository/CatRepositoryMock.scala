package scala.ddd.project.cat.infrastructure.repository

import org.scalamock.scalatest.MockFactory

import scala.concurrent.Future
import scala.ddd.project.cat.domain.{Cat, CatId, CatRepository}

trait CatRepositoryMock extends MockFactory {

  protected val repository: CatRepository[Future] = mock[CatRepository[Future]]

  protected def repositoryShouldFind(catOption: Option[Cat], catId: CatId): Unit =
    (repository.search _)
      .expects(catId)
      .returning(Future.successful(catOption))

  protected def repositoryShouldSave(cat: Cat): Unit =
    (repository.save _)
      .expects(cat)
      .returning(Future.successful())

}