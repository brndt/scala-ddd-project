package scala.ddd.project.cat.domain

final case class Cat(catId: CatId,
                     catName: CatName,
                     catAltNames: CatAltNames,
                     catDateOfBirth: CatDateOfBirth,
                     catCharacter: CatCharacter,
                     catWeight: CatWeight,
                     catEnergyLevel: CatEnergyLevel
                    )