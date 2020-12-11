package scala.ddd.project.cat.domain.exception

final case class CatAlreadyExistsException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)
