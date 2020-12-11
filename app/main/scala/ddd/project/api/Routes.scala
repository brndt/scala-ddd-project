package scala.ddd.project.api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, MediaTypes, StatusCode, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import spray.json.{JsString, JsValue, enrichAny}

import java.util.UUID
import scala.ddd.project.cat.domain.exception.CatAlreadyExistsException
import scala.ddd.project.catbreed.domain.exception.CatBreedNotFoundException
import scala.ddd.project.shared.infrastructure.marshaller.UuidMarshaller

final class Routes(entryPointDependencyContainer: EntryPointDependencyContainer) extends UuidMarshaller{

  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case exception: CatBreedNotFoundException =>
        complete(toErrorResponse(StatusCodes.NotFound, exception.getMessage))
      case exception: CatAlreadyExistsException =>
        complete(toErrorResponse(StatusCodes.BadRequest, exception.getMessage))
      case exception: RuntimeException =>
        complete(toErrorResponse(StatusCodes.InternalServerError, exception.getMessage))
    }

  private val cat = Route.seal(
    concat(
      get {
        path("cat_breed") {
          parameters("name") { breed =>
            entryPointDependencyContainer.getCatByBreedController.get(breed)
          }
        }
      },
      post {
        path("cat") {
          jsonBody { body =>
            entryPointDependencyContainer.postCatController.post(
              body("id").convertTo[UUID],
              body("name").convertTo[String],
              body("alt_names").convertTo[String],
              body("date_of_birth").convertTo[String],
              body("character").convertTo[String],
              body("weight").convertTo[Double],
              body("energy_level").convertTo[String]
            )
          }
        }
      }
    )
  )

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))

  private def toErrorResponse(status: StatusCode, message: String): HttpResponse = {
    val entity = HttpEntity(MediaTypes.`application/json`, s"""{"code":${status.intValue()},"message":"$message"}""")
    HttpResponse(status, entity = entity)
  }

  val all: Route = cat
}
