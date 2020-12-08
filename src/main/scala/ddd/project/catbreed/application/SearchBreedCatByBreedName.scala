package scala.ddd.project.catbreed.application

import scala.ddd.project.catbreed.domain.exception.CatBreedNotFoundException
import scala.ddd.project.catbreed.domain.{CatBreed, CatBreedName, CatBreedRepository}

import java.io.IOException
import scala.concurrent.{ExecutionContext, Future}

class SearchBreedCatByBreedName(catRepository: CatBreedRepository)(implicit executionContext: ExecutionContext) {
  def search(catBreed: String): Future[CatBreed] = {
    catRepository.searchCatsByBreed(CatBreedName(catBreed)).map {
      case Some(cat) => cat
      case None => throw CatBreedNotFoundException(s"Cat breed '$catBreed' not found")
    }
  }
}