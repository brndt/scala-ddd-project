package scala.ddd.project.api.controller

import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import scala.ddd.project.catbreed.application.SearchBreedCatByBreedName
import scala.ddd.project.catbreed.infrastructure.marshaller.CatBreedMarshaller

class GetCatBreedByBreedNameController(findCat: SearchBreedCatByBreedName) extends CatBreedMarshaller {
  def get(breed: String): StandardRoute = complete(findCat.search(breed))
}
