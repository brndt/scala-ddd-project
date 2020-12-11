package scala.ddd.project.cat.domain

import scala.ddd.project.shared.domain.StringMother

object CatNameMother {
  def random: CatName = CatName(StringMother.random)
}
