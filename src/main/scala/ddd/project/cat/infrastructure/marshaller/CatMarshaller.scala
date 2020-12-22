package scala.ddd.project.cat.infrastructure.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

import java.time.LocalDate
import java.util.UUID
import scala.ddd.project.cat.domain._

trait CatMarshaller extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object CatJsonFormat extends RootJsonFormat[Cat] {
    def write(cat: Cat): JsObject = JsObject(
      "id" -> JsString(cat.catId.value.toString),
      "name" -> JsString(cat.catName.value),
      "alt_names" -> JsString(cat.catAltNames.value),
      "date_of_birth" -> JsString(cat.catDateOfBirth.value.toString),
      "character" -> JsString(cat.catCharacter.value),
      "weight" -> JsNumber(cat.catWeight.value),
      "energy_level" -> JsString(cat.catEnergyLevel.value),
    )

    def read(value: JsValue): Cat = {
      value.asJsObject().getFields("id", "name", "alt_names", "date_of_birth", "character", "weight", "energy_level") match {
        case Seq(JsString(catId), JsString(catName), JsString(catAltNames), JsString(catDateOfBirth), JsString(catCharacter), JsString(catWeight), JsString(catEnergyLevel)) =>
          Cat(CatId(UUID.fromString(catId)), CatName(catName), CatAltNames(catAltNames), CatDateOfBirth(LocalDate.parse(catDateOfBirth)), CatCharacter(catCharacter), CatWeight(catWeight.toDouble), CatEnergyLevel(catEnergyLevel))
        case _ => throw DeserializationException("Cat expected")
      }
    }
  }

}