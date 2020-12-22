package scala.ddd.project.catbreed.domain

trait CatBreedRepository[P[_]] {
  def searchCatsByBreed(catBreed: CatBreedName): P[Option[CatBreed]]
}