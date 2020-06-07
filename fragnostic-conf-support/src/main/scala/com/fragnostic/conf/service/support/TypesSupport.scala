package com.fragnostic.conf.service.support

import scala.util.Try

trait TypesSupport {

  def toType[T](opt: Option[String], fn: String => T): Either[String, Option[T]] =
    opt map (
      valueString => Try(fn(valueString)) fold (
        error => Left(error.getMessage),
        valueType => Right(Option(valueType)))) getOrElse Right(None)

  def getType[T](either: Either[String, Option[T]], valueDefault: T): T =
    either fold (
      error => valueDefault,
      opt => opt map (value => value) getOrElse valueDefault)

}
