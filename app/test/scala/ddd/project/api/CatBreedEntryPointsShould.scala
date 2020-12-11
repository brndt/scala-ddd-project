package scala.ddd.project.api

import akka.http.scaladsl.model._

final class CatBreedEntryPointsShould extends EntryPointAcceptanceTest {

  feature("Search a cat breed") {

    scenario("Sending a get request with cat breed name") {

      When("I send a GET request to a cat endpoint with cat breed name 'beng'")
      val request = Get("/cat_breed?name=beng")

      Then("The status should be 200")
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
      }

      And("the response content should be")
      request ~> routes ~> check {
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should ===("""{"breed":"Bengal","description":"Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it."}""")
      }
    }

  }
}
