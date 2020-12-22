package scala.ddd.project.catbreed.application

import cats.implicits.catsSyntaxApplicativeId
import cats.syntax.flatMap._
import cats.{Applicative, FlatMap}

import scala.ddd.project.catbreed.domain.exception.CatBreedNotFoundException
import scala.ddd.project.catbreed.domain.{CatBreed, CatBreedName, CatBreedRepository}

trait SearchBreedCat[P[_]] {
  def search(catBreed: String): P[CatBreed]
}

final class SearchBreedCatByBreedName[P[_]](catRepository: CatBreedRepository[P])(implicit flatMap: FlatMap[P], applicative: Applicative[P]) extends SearchBreedCat[P] {
  def search(catBreed: String): P[CatBreed] = {
    catRepository.searchCatsByBreed(CatBreedName(catBreed)) flatMap {
      case Some(catBreed) => catBreed.pure[P]
      case None => throw CatBreedNotFoundException(s"Cat breed '$catBreed' not found")
    }
  }
}