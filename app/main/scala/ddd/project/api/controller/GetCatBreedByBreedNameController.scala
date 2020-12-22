package scala.ddd.project.api.controller

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, entity, onSuccess}
import akka.http.scaladsl.server.Route

import scala.concurrent.Future
import scala.ddd.project.catbreed.application.SearchBreedCatByBreedName
import scala.ddd.project.catbreed.domain.CatBreed
import scala.ddd.project.catbreed.infrastructure.marshaller.CatBreedMarshaller

final class GetCatBreedByBreedNameController(findCat: SearchBreedCatByBreedName[Future]) extends CatBreedMarshaller {
  def get(catBreed: String): Route = {
    onSuccess(findCat.search(catBreed)) { catBreed: CatBreed =>
      complete(StatusCodes.OK, catBreed)
    }
  }
}