package scala.ddd.project.catbreed.domain

import scala.ddd.project.shared.domain.StringMother

object CatBreedDescriptionMother {
  def random: CatBreedDescription = CatBreedDescription(StringMother.random)
}