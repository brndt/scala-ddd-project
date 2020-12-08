package scala.ddd.project.api.controller

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute

import scala.ddd.project.cat.application.CreateCat
import scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

class PostCatController(createCat: CreateCat) extends CatMarshaller {
  def post(catName: String, catAltNames: String, catDateOfBirth: String, catCharacter: String, catWeight: String, catEnergyLevel: String): StandardRoute = {
    createCat.create(catName, catAltNames, catDateOfBirth, catCharacter, catWeight, catEnergyLevel)
    complete(HttpResponse(NoContent))
  }
}