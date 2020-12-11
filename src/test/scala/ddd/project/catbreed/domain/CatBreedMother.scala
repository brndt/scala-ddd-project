package scala.ddd.project.catbreed.domain

object CatBreedMother {

  def apply(
             catBreedName: CatBreedName = CatBreedNameMother.random,
             catBreedDescription: CatBreedDescription = CatBreedDescriptionMother.random
           ): CatBreed = CatBreed(catBreedName, catBreedDescription)

  def random: CatBreed = apply()
}