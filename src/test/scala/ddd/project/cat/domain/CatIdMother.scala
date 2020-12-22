package scala.ddd.project.cat.domain

import java.util.UUID

object CatIdMother {
  def random: CatId = CatId(UUID.randomUUID())
}
