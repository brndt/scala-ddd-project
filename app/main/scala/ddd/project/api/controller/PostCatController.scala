package scala.ddd.project.api.controller

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onSuccess}
import akka.http.scaladsl.server.Route

import java.util.UUID
import scala.concurrent.Future
import scala.ddd.project.cat.application.CreateCat
import scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

final class PostCatController(createCat: CreateCat[Future]) extends CatMarshaller {
  def post(catId: UUID, catName: String, catAltNames: String, catDateOfBirth: String, catCharacter: String, catWeight: Double, catEnergyLevel: String): Route = {
    onSuccess(createCat.create(catId, catName, catAltNames, catDateOfBirth, catCharacter, catWeight, catEnergyLevel)) {
      complete(StatusCodes.Created)
    }
  }
}