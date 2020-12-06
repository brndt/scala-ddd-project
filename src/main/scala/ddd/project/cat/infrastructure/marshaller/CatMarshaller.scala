package main.scala.ddd.project.cat.infrastructure.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import main.scala.ddd.project.cat.domain.{Cat, CatBreed, CatDescription}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsObject, JsString, JsValue, RootJsonFormat}

trait CatMarshaller extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object CatJsonFormat extends RootJsonFormat[Cat] {
    def write(c: Cat) = JsObject(
      "breed" -> JsString(c.name.value),
      "description" -> JsString(c.description.value)
    )

    def read(value: JsValue): Cat = {
      value.asJsObject().getFields("name", "description") match {
        case Seq(JsString(name), JsString(description)) =>
          new Cat(CatBreed(name), CatDescription(description))
        case _ => throw new DeserializationException("Color expected")
      }
    }
  }

}