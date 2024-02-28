package com.fragnostic.conf.base.service.support

import scala.util.Try

@SerialVersionUID(74567674352L)
trait TypesSupport {

  def toBoolean(opt: String): Either[String, Boolean] = {
    toType(opt, (a: String) => a.toBoolean)
  }

  def toShort(opt: String): Either[String, Short] = {
    toType(opt, (a: String) => a.toShort)
  }

  def toBigDecimal(opt: String): Either[String, BigDecimal] = {
    toType(opt, (a: String) => BigDecimal(a))
  }

  def toInt(opt: String): Either[String, Int] = {
    toType(opt, (a: String) => a.toInt)
  }

  def toLong(opt: String): Either[String, Long] = {
    toType(opt, (a: String) => a.toLong)
  }

  def toType[T](theString: String, fn: String => T): Either[String, T] = {
    Try(fn(theString)) fold (
      error => Left(error.getMessage),
      valueType => Right(valueType) //
    )
  }

  def getType[T](either: Either[String, T], valueDefault: T): T = {
    either fold (
      error => valueDefault,
      value => value //
    )
  }

}
