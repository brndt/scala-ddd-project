package scala.ddd.project.cat.domain

import scala.ddd.project.shared.domain.LocalDateMother

object CatDateOfBirthMother {
  def random: CatDateOfBirth = CatDateOfBirth(LocalDateMother.random)
}
