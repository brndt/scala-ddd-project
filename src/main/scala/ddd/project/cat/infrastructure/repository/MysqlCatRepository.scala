package scala.ddd.project.cat.infrastructure.repository

import scala.concurrent.Future
import scala.ddd.project.cat.domain.{Cat, CatRepository}

final class MysqlCatRepository extends CatRepository {
  override def save(cat: Cat): Future[Unit] = {
    // TODO
    Future.successful()
  }
}