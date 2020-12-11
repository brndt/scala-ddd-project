package scala.ddd.project.cat.domain

import scala.concurrent.Future

trait CatRepository {
  def save(cat: Cat): Future[Unit]
  def search(catId: CatId): Future[Option[Cat]]
}
