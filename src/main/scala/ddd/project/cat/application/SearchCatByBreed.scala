package main.scala.ddd.project.cat.application

import main.scala.ddd.project.cat.domain.{Cat, CatBreed, CatRepository}

import scala.concurrent.Future

class SearchCatByBreed(catRepository: CatRepository) {
  def search(catBreed: String): Future[Option[Cat]] = {
    catRepository.searchCatsByBreed(CatBreed(catBreed))
  }
}