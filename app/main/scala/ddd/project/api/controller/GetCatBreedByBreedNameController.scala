package main.scala.ddd.project.api.controller

import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import main.scala.ddd.project.cat.application.SearchBreedCatByBreedName
import main.scala.ddd.project.cat.infrastructure.marshaller.CatBreedMarshaller

class GetCatBreedByBreedNameController(findCat: SearchBreedCatByBreedName) extends CatBreedMarshaller {
  def get(breed: String): StandardRoute = complete(findCat.search(breed))
}
