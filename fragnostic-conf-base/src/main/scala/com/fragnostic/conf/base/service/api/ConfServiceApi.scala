package com.fragnostic.conf.base.service.api

import java.util.Locale

trait ConfServiceApi {

  def confServiceApi: ConfServiceApi

  trait ConfServiceApi {

    def getString(key: String): Either[String, Option[String]]

    def getString(locale: Locale, key: String): Either[String, Option[String]]

    def getShort(key: String): Either[String, Option[Short]]

    def getInt(key: String): Either[String, Option[Int]]

    def getLong(key: String): Either[String, Option[Long]]

    def getBoolean(key: String): Either[String, Option[Boolean]]

  }

}