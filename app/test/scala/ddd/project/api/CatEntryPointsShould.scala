package scala.ddd.project.api

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import scalikejdbc.scalikejdbcSQLInterpolationImplicitDef

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.ddd.project.cat.domain.CatMother
import scala.ddd.project.cat.infrastructure.marshaller.CatMarshaller

final class CatEntryPointsShould extends EntryPointAcceptanceTest with CatMarshaller {

  override def afterEach(): Unit = {
    super.afterEach()
    sql"truncate table cat".execute.apply()(catDependencyContainer.DBSession)
  }

  override def afterAll(): Unit = {
    super.afterAll()
    catDependencyContainer.DBSession.close()
  }

  feature("Create a new cat") {

    scenario("Sending a post request with cat body when there's no such a cat") {
      Given("there is a cat")
      val cat = CatMother.random
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
      val cat = CatMother.random
      val catEntity = Marshal(cat).to[MessageEntity].futureValue
      Await.ready(catDependencyContainer.mysqlCatRepository.save(cat), Duration.Inf)

      When("I send a POST request to a cat endpoint")
      val request = Post("/cat").withEntity(catEntity)

      Then("the response status code should be 400")
      request ~> routes ~> check {
        status should ===(StatusCodes.BadRequest)
      }

      Then("the response content should be")
      request ~> routes ~> check {
        entityAs[String] should ===(s"""{"code":400,"message":"Cat with id ${cat.catId.value} already exists"}""")
      }
    }
  }


}
