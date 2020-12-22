package scala.ddd.project.cat.domain

import scala.ddd.project.shared.domain.StringMother

object CatCharacterMother {
  def random: CatCharacter = CatCharacter(StringMother.random)
}
