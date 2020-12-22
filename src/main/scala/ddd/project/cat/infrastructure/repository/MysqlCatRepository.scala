package scala.ddd.project.cat.infrastructure.repository

import scalikejdbc.{DBSession, scalikejdbcSQLInterpolationImplicitDef}

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.ddd.project.cat.domain._

final class MysqlCatRepository(implicit val DBSession: DBSession, executionContext: ExecutionContext) extends CatRepository[Future] {
  override def save(cat: Cat): Future[Unit] = {
    sql"insert into cat values (${cat.catId.value.toString}, ${cat.catName.value}, ${cat.catAltNames.value}, ${cat.catDateOfBirth.value}, ${cat.catCharacter.value}, ${cat.catWeight.value}, ${cat.catEnergyLevel.value})".update.apply()
    Future.successful()
  }

  override def search(catId: CatId): Future[Option[Cat]] = {
    Future(
      sql"select id, name, alt_names, date_of_birth, `character`, weight, energy_level from cat where id = ${catId.value.toString}".map(result =>
        Cat(
          CatId(UUID.fromString(result.string("id"))),
          CatName(result.string("name")),
          CatAltNames(result.string("alt_names")),
          CatDateOfBirth(result.localDate("date_of_birth")),
          CatCharacter(result.string("character")),
          CatWeight(result.double("weight")),
          CatEnergyLevel(result.string("energy_level"))
        )
      ).single.apply())
  }
}