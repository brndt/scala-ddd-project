package scala.ddd.project.shared.domain

import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom

object LocalDateMother {
  def random: LocalDate = {
    val randomDay = ThreadLocalRandom.current().nextLong(LocalDate.of(1000, 1, 1).toEpochDay, LocalDate.of(9999, 12, 12).toEpochDay)
    LocalDate.ofEpochDay(randomDay)
  }
}
