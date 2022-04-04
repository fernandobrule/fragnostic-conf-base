package com.fragnostic.conf.base.service.api

import java.util.Locale

trait ConfServiceApi {

  def confServiceApi: ConfServiceApi

  trait ConfServiceApi {

    def getString(key: String): Either[String, String]

    def getString(locale: Locale, key: String): Either[String, String]

    def getShort(key: String): Either[String, Short]

    def getInt(key: String): Either[String, Int]

    def getLong(key: String): Either[String, Long]

    def getBoolean(key: String): Either[String, Boolean]

  }

}