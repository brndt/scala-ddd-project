package scala.ddd.project.api

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import org.scalatest.BeforeAndAfterEach
import scalikejdbc.{DBSession, scalikejdbcSQLInterpolationImplicitDef}

import java.time.LocalDate
import java.util.UUID
import scala.ddd.project.cat.domain.{Cat, CatAltNames, CatCharacter, CatDateOfBirth, CatEnergyLevel, CatId, CatName, CatWeight}
import scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

final class CatEntryPointsShould extends EntryPointAcceptanceTest with CatMarshaller {

  override def afterEach(): Unit = {
    super.afterEach()
    sql"truncate table cat".execute.apply()(catDependencyContainer.DBSession)
  }

  feature("Create a new cat") {

    scenario("Sending a post request with cat body when there's no such a cat") {
      Given("there is a cat")
      val cat = Cat(CatId(UUID.randomUUID()), CatName("random_name"), CatAltNames("random other names"), CatDateOfBirth(LocalDate.now()), CatCharacter("random character"), CatWeight(4.2), CatEnergyLevel("high"))
      val catEntity = Marshal(cat).to[MessageEntity].futureValue

      When("I send a POST request to a cat endpoint")
      val request = Post("/cat").withEntity(catEntity)

      Then("The status should be 'Created'")
      request ~> routes ~> check {
        status should ===(StatusCodes.Created)
      }
    }

    scenario("Sending a post request with cat body when he/she already exists") {
      Given("there is a cat")
      val cat = Cat(CatId(UUID.randomUUID()), CatName("random_name"), CatAltNames("random other names"), CatDateOfBirth(LocalDate.now()), CatCharacter("random character"), CatWeight(4.2), CatEnergyLevel("high"))
      val catEntity = Marshal(cat).to[MessageEntity].futureValue
      Post("/cat").withEntity(catEntity) ~> routes

      When("I send a POST request to a cat endpoint")
      val request = Post("/cat").withEntity(catEntity)

      Then("the response status code should be 400")
      request ~> routes ~> check {
        status should ===(StatusCodes.BadRequest)
      }

      Then("the response content should be")
      request ~> routes ~> check {
        entityAs[String] should ===(s"Cat with id ${cat.catId.value.toString} already exists")
      }
    }
  }


}
