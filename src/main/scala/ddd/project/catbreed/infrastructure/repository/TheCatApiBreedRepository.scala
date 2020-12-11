package scala.ddd.project.catbreed.infrastructure.repository

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import scala.ddd.project.catbreed.domain.{CatBreed, CatBreedName, CatBreedRepository}
import scala.ddd.project.catbreed.infrastructure.marshaller._
import akka.actor.ClassicActorSystemProvider
import scala.concurrent.{ExecutionContext, Future}

final class TheCatApiBreedRepository(implicit executionContext: ExecutionContext, system: ClassicActorSystemProvider) extends CatBreedRepository with CatBreedMarshaller {
  private val apiURL = "https://api.thecatapi.com/v1"

  override def searchCatsByBreed(catBreed: CatBreedName): Future[Option[CatBreed]] = {
    Http().singleRequest(HttpRequest(
      method = HttpMethods.GET,
      uri = apiURL + "/breeds/search?q=" + catBreed.value
    )).flatMap(response =>
      response.status match {
        case StatusCodes.OK =>
          Unmarshal(response).to[List[CatBreed]].map(_.headOption)
        case _ =>
          throw new RuntimeException(s"Request failed with status ${response.status} and error ${response.entity}")
      })
  }

}
