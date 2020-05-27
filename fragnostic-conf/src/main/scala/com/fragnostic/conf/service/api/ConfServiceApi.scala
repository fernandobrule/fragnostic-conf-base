package com.fragnostic.conf.service.api

import java.util.Locale

import com.fragnostic.i18n.api.ResourceI18n

/**
 * Configuration from Cache -> Environment -> Properties -> DB
 */
trait ConfServiceApi {

  def confServiceApi: ConfServiceApi

  trait ConfServiceApi {

    def validate(vars: List[String]): Map[String, String]

    def set(key: String, value: String): Either[String, String]

    def set(key: String, value: Short): Either[String, String]

    def set(key: String, value: Int): Either[String, String]

    def set(key: String, value: Long): Either[String, String]

    def getString(locale: Option[Locale] = None, props: Option[ResourceI18n] = None, key: String): Either[String, Option[String]]

    def getShort(props: Option[ResourceI18n] = None, key: String): Either[String, Option[Short]]

    def getInt(props: Option[ResourceI18n] = None, key: String): Either[String, Option[Int]]

    def getLong(props: Option[ResourceI18n] = None, key: String): Either[String, Option[Long]]

    def del(key: String): Either[String, Option[String]]

    def delAllKeys(): Either[String, String]

  }

}
