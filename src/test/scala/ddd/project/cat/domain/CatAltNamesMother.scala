package scala.ddd.project.cat.domain

import scala.ddd.project.shared.domain.StringMother

object CatAltNamesMother {
  def random: CatAltNames = CatAltNames(StringMother.random)
}
