package scala.ddd.project.api

import akka.http.scaladsl.model._

final class CatBreedEntryPointsShould extends EntryPointAcceptanceTest {

  "return cat breed (GET /cat_breed?name=beng)" in {
    Get("/cat_breed?name=beng") ~> routes ~> check {
      status should ===(StatusCodes.OK)
      contentType should ===(ContentTypes.`application/json`)
      entityAs[String] should ===("""{"breed":"Bengal","description":"Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it."}""")
    }
  }

}
