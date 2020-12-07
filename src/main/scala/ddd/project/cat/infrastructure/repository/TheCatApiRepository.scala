package main.scala.ddd.project.cat.infrastructure.repository

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import main.scala.ddd.project.cat.domain.{Cat, CatBreed, CatRepository}
import main.scala.ddd.project.cat.infrastructure.marshaller._
import akka.actor.ClassicActorSystemProvider
import scala.concurrent.{ExecutionContext, Future}
import java.io.IOException

class TheCatApiRepository(implicit executionContext: ExecutionContext, system: ClassicActorSystemProvider) extends CatRepository with CatMarshaller {
  private val apiURL = "https://api.thecatapi.com/v1"

  override def searchCatsByBreed(catBreed: CatBreed): Future[Option[Cat]] = {
    Http().singleRequest(HttpRequest(
      method = HttpMethods.GET,
      uri = apiURL + "/breeds/search?q=" + catBreed.value
    )).flatMap(response =>
      response.status match {
        case StatusCodes.OK =>
          Unmarshal(response).to[List[Cat]].map(_.headOption)
        case _ =>
          throw new RuntimeException(s"Request failed with status ${response.status} and error ${response.entity}")
      })
  }

}
