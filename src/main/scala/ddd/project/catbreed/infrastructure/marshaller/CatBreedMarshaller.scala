package scala.ddd.project.catbreed.infrastructure.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, DeserializationException, JsObject, JsString, JsValue, RootJsonFormat}

import scala.ddd.project.catbreed.domain.{CatBreed, CatBreedDescription, CatBreedName}

trait CatBreedMarshaller extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object CatBreedJsonFormat extends RootJsonFormat[CatBreed] {
    def write(catBreed: CatBreed): JsObject = JsObject(
      "breed" -> JsString(catBreed.name.value),
      "description" -> JsString(catBreed.description.value)
    )

    def read(value: JsValue): CatBreed = {
      value.asJsObject().getFields("name", "description") match {
        case Seq(JsString(name), JsString(description)) =>
          CatBreed(CatBreedName(name), CatBreedDescription(description))
        case _ => throw DeserializationException("Cat breed expected")
      }
    }
  }

}