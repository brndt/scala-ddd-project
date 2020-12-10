package scala.ddd.project.api

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._

import java.time.LocalDate
import java.util.UUID
import scala.ddd.project.cat.domain.{Cat, CatAltNames, CatCharacter, CatDateOfBirth, CatEnergyLevel, CatId, CatName, CatWeight}
import scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

final class CatEntryPointsShould extends EntryPointAcceptanceTest with CatMarshaller {

  "post cat (POST /cat)" in {

    val cat = Cat(CatId(UUID.randomUUID()), CatName("random_name"), CatAltNames("random other names"), CatDateOfBirth(LocalDate.now()), CatCharacter("random character"), CatWeight(4.2), CatEnergyLevel("high"))
    val catEntity = Marshal(cat).to[MessageEntity].futureValue

    Post("/cat").withEntity(catEntity) ~> routes ~> check {
      status should ===(StatusCodes.Created)
    }
  }

}
