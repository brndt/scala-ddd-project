package scala.ddd.project.cat.infrastructure.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import scala.ddd.project.cat.domain.{CatBreed, CatBreedName, CatBreedDescription}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsObject, JsString, JsValue, RootJsonFormat}

trait CatBreedMarshaller extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object CatJsonFormat extends RootJsonFormat[CatBreed] {
    def write(c: CatBreed) = JsObject(
      "breed" -> JsString(c.name.value),
      "description" -> JsString(c.description.value)
    )

    def read(value: JsValue): CatBreed = {
      value.asJsObject().getFields("name", "description") match {
        case Seq(JsString(name), JsString(description)) =>
          new CatBreed(CatBreedName(name), CatBreedDescription(description))
        case _ => throw new DeserializationException("Color expected")
      }
    }
  }

}