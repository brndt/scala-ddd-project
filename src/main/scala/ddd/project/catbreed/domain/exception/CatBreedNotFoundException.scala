package scala.ddd.project.catbreed.domain.exception

final case class CatBreedNotFoundException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)