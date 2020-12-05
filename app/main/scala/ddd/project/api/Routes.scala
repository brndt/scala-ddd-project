package main.scala.ddd.project.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class Routes(entryPointDependencyContainer: EntryPointDependencyContainer) {

  private val cat = get {
    path("cat") {
      parameters("breed") { breed =>
        entryPointDependencyContainer.getCatByBreedController.get(breed)
      }
    }
  }

  val all: Route = cat
}
