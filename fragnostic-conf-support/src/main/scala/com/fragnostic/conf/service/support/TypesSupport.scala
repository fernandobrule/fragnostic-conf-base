package com.fragnostic.conf.service.support

import scala.util.Try

trait TypesSupport {

  def toInt(opt: Option[String]): Either[String, Option[Int]] =
    opt map (
      valueString => Try(valueString.toInt) fold (
        error => Left(error.getMessage),
        valueInt => Right(Option(valueInt)))) getOrElse Right(None)

  def toShort(opt: Option[String]): Either[String, Option[Short]] =
    opt map (
      valueString => Try(valueString.toShort) fold (
        error => Left(error.getMessage),
        valueShort => Right(Option(valueShort)))) getOrElse Right(None)

  def toLong(opt: Option[String]): Either[String, Option[Long]] =
    opt map (
      valueString => Try(valueString.toLong) fold (
        error => Left(error.getMessage),
        valueLong => Right(Option(valueLong)))) getOrElse Right(None)

}
