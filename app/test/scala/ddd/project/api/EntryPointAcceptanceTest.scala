package scala.ddd.project.api

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration._
import akka.http.scaladsl.testkit.RouteTestTimeout
import akka.testkit.TestDuration

import scala.ddd.project.cat.infrastructure.dependency_injection.CatDependencyContainer

trait EntryPointAcceptanceTest extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  implicit val timeout: RouteTestTimeout = RouteTestTimeout(5.seconds.dilated)
  val entryPointDependencies = new EntryPointDependencyContainer(new CatDependencyContainer())
  val routes: Route = new Routes(entryPointDependencies).all
}
