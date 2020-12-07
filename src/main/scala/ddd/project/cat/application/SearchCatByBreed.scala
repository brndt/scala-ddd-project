package main.scala.ddd.project.cat.application

import main.scala.ddd.project.cat.domain.exception.CatBreedNotFoundException
import main.scala.ddd.project.cat.domain.{Cat, CatBreed, CatRepository}

import java.io.IOException
import scala.concurrent.{ExecutionContext, Future}

class SearchCatByBreed(catRepository: CatRepository)(implicit executionContext: ExecutionContext) {
  def search(catBreed: String): Future[Cat] = {
    catRepository.searchCatsByBreed(CatBreed(catBreed)).map {
      case Some(s) => s
      case None => throw CatBreedNotFoundException(s"Cat breed '$catBreed' not found")
    }
  }
}