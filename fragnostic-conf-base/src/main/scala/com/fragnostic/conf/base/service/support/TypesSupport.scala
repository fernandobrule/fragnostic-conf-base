package com.fragnostic.conf.base.service.support

import scala.util.Try

trait TypesSupport {

  def toShort(opt: Option[String]): Either[String, Option[Short]] =
    toType(opt, (a: String) => a.toShort)

  def toInt(opt: Option[String]): Either[String, Option[Int]] =
    toType(opt, (a: String) => a.toInt)

  def toLong(opt: Option[String]): Either[String, Option[Long]] =
    toType(opt, (a: String) => a.toLong)

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
