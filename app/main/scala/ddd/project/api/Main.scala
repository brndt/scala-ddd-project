package scala.ddd.project.api

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import scala.ddd.project.cat.infrastructure.dependency_injection.CatDependencyContainer

import scala.concurrent.ExecutionContext
import scala.language.postfixOps
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContext = system.executionContext

    val entryPointDependencies = new EntryPointDependencyContainer(new CatDependencyContainer())

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(new Routes(entryPointDependencies).all)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())

  }
}
