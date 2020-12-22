package scala.ddd.project.shared.domain

import scala.util.Random

object StringMother {
  def random: String = Random.alphanumeric take Random.nextInt(20) + 1 mkString ""
}