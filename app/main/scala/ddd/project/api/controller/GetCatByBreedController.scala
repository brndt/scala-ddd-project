package main.scala.ddd.project.api.controller

import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import main.scala.ddd.project.cat.application.SearchCatByBreed
import main.scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

class GetCatByBreedController(findCat: SearchCatByBreed) extends CatMarshaller {
  def get(breed: String): StandardRoute = complete(findCat.search(breed))
}
