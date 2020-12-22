package scala.ddd.project.cat.application

import cats.FlatMap
import cats.syntax.flatMap._

import java.time.LocalDate
import java.util.UUID
import scala.concurrent.ExecutionContext
import scala.ddd.project.cat.domain._
import scala.ddd.project.cat.domain.exception.CatAlreadyExistsException

final class CreateCat[P[_]](catRepository: CatRepository[P])(implicit flatMap: FlatMap[P], executionContext: ExecutionContext) {
  def create(catId: UUID, catName: String, catAltNames: String, catDateOfBirth: String, catCharacter: String, catWeight: Double, catEnergyLevel: String): P[Unit] = {
    catRepository.search(CatId(catId)) flatMap {
      case Some(_) => throw CatAlreadyExistsException(s"Cat with id $catId already exists")
      case None =>
        val cat = Cat(CatId(catId), CatName(catName), CatAltNames(catAltNames), CatDateOfBirth(LocalDate.parse(catDateOfBirth)), CatCharacter(catCharacter), CatWeight(catWeight), CatEnergyLevel(catEnergyLevel))
        catRepository.save(cat)
    }
  }
}