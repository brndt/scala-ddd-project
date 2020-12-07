package main.scala.ddd.project.cat.domain.exception

final case class CatBreedNotFoundException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)
