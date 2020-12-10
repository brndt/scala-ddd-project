package scala.ddd.project.cat.application

import java.time.LocalDate
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.ddd.project.cat.domain.exception.CatAlreadyExistsException
import scala.ddd.project.cat.domain.{Cat, CatAltNames, CatCharacter, CatDateOfBirth, CatEnergyLevel, CatId, CatName, CatRepository, CatWeight}

final class CreateCat(catRepository: CatRepository)(implicit executionContext: ExecutionContext) {
  def create(catId: UUID, catName: String, catAltNames: String, catDateOfBirth: String, catCharacter: String, catWeight: Double, catEnergyLevel: String): Future[Unit] = {
    catRepository.search(CatId(catId)).flatMap {
      case Some(_) => throw CatAlreadyExistsException(s"Cat with id '$catId' already exists")
      case None =>
        val cat = Cat(CatId(catId), CatName(catName), CatAltNames(catAltNames), CatDateOfBirth(LocalDate.parse(catDateOfBirth)), CatCharacter(catCharacter), CatWeight(catWeight), CatEnergyLevel(catEnergyLevel))
        catRepository.save(cat)
    }
  }
}