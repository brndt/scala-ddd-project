package scala.ddd.project.cat.application

import scala.concurrent.{ExecutionContext, Future}
import scala.ddd.project.cat.domain.{Cat, CatAltNames, CatCharacter, CatDateOfBirth, CatEnergyLevel, CatName, CatRepository, CatWeight}

final class CreateCat(catRepository: CatRepository)(implicit executionContext: ExecutionContext) {
  def create(catName: String, catAltNames: String, catDateOfBirth: String, catCharacter: String, catWeight: String, catEnergyLevel: String): Future[Unit] = {

    val cat = Cat(CatName(catName), CatAltNames(catAltNames), CatDateOfBirth(catDateOfBirth), CatCharacter(catCharacter), CatWeight(catWeight), CatEnergyLevel(catEnergyLevel))

    catRepository.save(cat)
  }
}