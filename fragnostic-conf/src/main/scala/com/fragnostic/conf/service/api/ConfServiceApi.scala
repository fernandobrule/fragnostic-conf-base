package com.fragnostic.conf.service.api

import java.util.Locale

import com.fragnostic.i18n.api.ResourceI18n

/**
 * Configuration from Cache -> Environment -> Properties -> DB
 */
trait ConfServiceApi {

  def confService: ConfServiceApi

  trait ConfServiceApi {

    def validate(vars: List[String]): Map[String, String]

    def getString(locale: Option[Locale] = None, i18n: Option[ResourceI18n] = None, key: String): Either[String, Option[String]]

    def getInt(name: String): Either[String, Int]

    def getLong(str: String): Either[String, Int]

  }

}
