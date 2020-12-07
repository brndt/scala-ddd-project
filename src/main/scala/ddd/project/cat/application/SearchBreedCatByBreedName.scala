package scala.ddd.project.cat.application

import scala.ddd.project.cat.domain.exception.CatBreedNotFoundException
import scala.ddd.project.cat.domain.{CatBreed, CatBreedName, CatBreedRepository}

import java.io.IOException
import scala.concurrent.{ExecutionContext, Future}

class SearchBreedCatByBreedName(catRepository: CatBreedRepository)(implicit executionContext: ExecutionContext) {
  def search(catBreed: String): Future[CatBreed] = {
    catRepository.searchCatsByBreed(CatBreedName(catBreed)).map {
      case Some(s) => s
      case None => throw CatBreedNotFoundException(s"Cat breed '$catBreed' not found")
    }
  }
}