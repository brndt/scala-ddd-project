package scala.ddd.project.catbreed.domain

import scala.concurrent.Future

trait CatBreedRepository {
  def searchCatsByBreed(catBreed: CatBreedName): Future[Option[CatBreed]]
}
