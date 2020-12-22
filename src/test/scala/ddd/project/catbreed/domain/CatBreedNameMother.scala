package scala.ddd.project.catbreed.domain

import scala.ddd.project.shared.domain.StringMother

object CatBreedNameMother {
  def random: CatBreedName = CatBreedName(StringMother.random)
}