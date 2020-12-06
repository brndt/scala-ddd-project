package main.scala.ddd.project.cat.domain

import scala.concurrent.Future

trait CatRepository {
  def searchCatsByBreed(catBreed: CatBreed): Future[Option[Cat]]
}
