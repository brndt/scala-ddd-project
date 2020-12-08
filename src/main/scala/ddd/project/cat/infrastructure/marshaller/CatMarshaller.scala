package scala.ddd.project.cat.infrastructure.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, DeserializationException, JsObject, JsString, JsValue, RootJsonFormat}

import scala.ddd.project.cat.domain.{Cat, CatAltNames, CatCharacter, CatDateOfBirth, CatEnergyLevel, CatName, CatWeight}
import scala.ddd.project.catbreed.domain.{CatBreed, CatBreedDescription, CatBreedName}

trait CatMarshaller extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object CatJsonFormat extends RootJsonFormat[Cat] {
    def write(cat: Cat): JsObject = JsObject(
      "name" -> JsString(cat.catName.value),
      "alt_names" -> JsString(cat.catAltNames.value),
      "date_of_birth" -> JsString(cat.catDateOfBirth.value),
      "character" -> JsString(cat.catCharacter.value),
      "weight" -> JsString(cat.catWeight.value),
      "energy_level" -> JsString(cat.catEnergyLevel.value),
    )

    def read(value: JsValue): Cat = {
      value.asJsObject().getFields("name", "alt_names", "date_of_birth", "character", "weight", "energy_level") match {
        case Seq(JsString(catName), JsString(catAltNames), JsString(catDateOfBirth), JsString(catCharacter), JsString(catWeight), JsString(catEnergyLevel)) =>
          Cat(CatName(catName), CatAltNames(catAltNames), CatDateOfBirth(catDateOfBirth), CatCharacter(catCharacter), CatWeight(catWeight), CatEnergyLevel(catEnergyLevel))
        case _ => throw DeserializationException("Cat expected")
      }
    }
  }

}