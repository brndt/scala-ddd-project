package scala.ddd.project.api

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{BeforeAndAfterEach, FeatureSpec, GivenWhenThen, Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration._
import akka.http.scaladsl.testkit.RouteTestTimeout
import akka.testkit.TestDuration

import scala.ddd.project.catbreed.infrastructure.dependency_injection.CatDependencyContainer

trait EntryPointAcceptanceTest extends FeatureSpec with GivenWhenThen with Matchers with ScalaFutures with ScalatestRouteTest with BeforeAndAfterEach {
  val environment = "dev"
  implicit val timeout: RouteTestTimeout = RouteTestTimeout(30.seconds.dilated)
  val catDependencyContainer = new CatDependencyContainer(environment)
  val entryPointDependencies = new EntryPointDependencyContainer(catDependencyContainer)
  val routes: Route = new Routes(entryPointDependencies).all
}
