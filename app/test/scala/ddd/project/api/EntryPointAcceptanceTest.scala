package scala.ddd.project.api

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration._
import akka.http.scaladsl.testkit.RouteTestTimeout
import akka.testkit.TestDuration

import scala.ddd.project.catbreed.infrastructure.dependency_injection.CatDependencyContainer

trait EntryPointAcceptanceTest extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {

  val environment = "dev"
  implicit val timeout: RouteTestTimeout = RouteTestTimeout(30.seconds.dilated)
  val entryPointDependencies = new EntryPointDependencyContainer(new CatDependencyContainer(environment))
  val routes: Route = new Routes(entryPointDependencies).all
}
