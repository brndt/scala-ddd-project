package scala.ddd.project.cat.domain

import scala.ddd.project.shared.domain.StringMother

object CatEnergyLevelMother {
  def random: CatEnergyLevel = CatEnergyLevel(StringMother.random)
}
