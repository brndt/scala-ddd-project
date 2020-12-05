package main.scala.ddd.project.cat.infrastructure.repository

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal
import main.scala.ddd.project.cat.domain.{Cat, CatBreed, CatRepository}
import main.scala.ddd.project.cat.infrastructure.marshaller._

import scala.concurrent.{ExecutionContextExecutor, Future}

class TheCatApiRepository extends CatRepository with CatMarshaller {
  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  override def searchCatsByBreed(catBreed: CatBreed): Future[Cat] = {
    Http().singleRequest(HttpRequest(
      method = HttpMethods.GET,
      uri = "https://api.thecatapi.com/v1/breeds/search?q="+catBreed.value
    )).flatMap(Unmarshal(_).to[List[Cat]]).map(_.head)
  }
}
