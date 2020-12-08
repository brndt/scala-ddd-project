package scala.ddd.project.api

import akka.http.scaladsl.model._

final class CatBreedEntryPointsShould extends EntryPointAcceptanceTest {

  "UserRoutes" should {
    "return no users if no present (GET /cat_breed)" in {

      val request = HttpRequest(uri = "/cat_breed?name=beng")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[String] should ===("""{"breed":"Bengal","description":"Bengals are a lot of fun to live with, but they're definitely not the cat for everyone, or for first-time cat owners. Extremely intelligent, curious and active, they demand a lot of interaction and woe betide the owner who doesn't provide it."}""")
      }
    }
  }
}
