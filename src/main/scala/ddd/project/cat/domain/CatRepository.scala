package scala.ddd.project.cat.domain

trait CatRepository[P[_]] {
  def save(cat: Cat): P[Unit]

  def search(catId: CatId): P[Option[Cat]]
}
