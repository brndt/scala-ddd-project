package scala.ddd.project.api.controller

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.{Created, NoContent}
import akka.http.scaladsl.server.Directives.{complete, onSuccess}
import akka.http.scaladsl.server.{Route}

import java.util.UUID
import scala.ddd.project.cat.application.CreateCat
import scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

class PostCatController(createCat: CreateCat) extends CatMarshaller {
  def post(catId: UUID, catName: String, catAltNames: String, catDateOfBirth: String, catCharacter: String, catWeight: Double, catEnergyLevel: String): Route = {
    onSuccess(createCat.create(catId, catName, catAltNames, catDateOfBirth, catCharacter, catWeight, catEnergyLevel)) {
      complete(HttpResponse(Created))
    }
  }
}