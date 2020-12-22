package scala.ddd.project.cat.domain

import scala.ddd.project.shared.domain.DoubleMother

object CatWeightMother {
  def random: CatWeight = CatWeight(DoubleMother.random)
}