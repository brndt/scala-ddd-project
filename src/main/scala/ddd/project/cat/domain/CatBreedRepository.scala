package scala.ddd.project.cat.domain

import scala.concurrent.Future

trait CatBreedRepository {
  def searchCatsByBreed(catBreed: CatBreedName): Future[Option[CatBreed]]
}
