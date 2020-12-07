package main.scala.ddd.project.api

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import main.scala.ddd.project.cat.domain.exception.CatBreedNotFoundException

class Routes(entryPointDependencyContainer: EntryPointDependencyContainer) {

  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case exception: CatBreedNotFoundException =>
        complete(HttpResponse(StatusCodes.NotFound, entity = exception.getMessage))
      case exception: RuntimeException =>
        complete(HttpResponse(StatusCodes.InternalServerError, entity = exception.getMessage))
    }

  private val cat = Route.seal(get {
    path("cat_breed") {
      parameters("name") { breed =>
        entryPointDependencyContainer.getCatByBreedController.get(breed)
      }
    }
  })

  val all: Route = cat
}
