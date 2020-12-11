package scala.ddd.project.cat.domain

object CatMother {

  def apply(
             catId: CatId = CatIdMother.random,
             catName: CatName = CatNameMother.random,
             catAltNames: CatAltNames = CatAltNamesMother.random,
             catDateOfBirth: CatDateOfBirth = CatDateOfBirthMother.random,
             catCharacter: CatCharacter = CatCharacterMother.random,
             catWeight: CatWeight = CatWeightMother.random,
             catEnergyLevel: CatEnergyLevel = CatEnergyLevelMother.random
           ): Cat = Cat(catId, catName, catAltNames, catDateOfBirth, catCharacter, catWeight, catEnergyLevel)

  def random: Cat = apply()
}
