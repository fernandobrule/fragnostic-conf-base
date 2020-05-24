package com.fragnostic.conf.service.api

import java.util.Locale

import com.fragnostic.i18n.api.ResourceI18n

trait CacheServiceApi {

  def cacheServiceApi: CacheServiceApi

  trait CacheServiceApi {

    def set(key: String, value: String): Either[String, String]

    def set(key: String, value: Short): Either[String, String]

    def set(key: String, value: Int): Either[String, String]

    def set(key: String, value: Long): Either[String, String]

    def getString(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[String] = None): Option[String]

    def getShort(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[Short] = None): Option[Short]

    def getInt(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[Int] = None): Option[Int]

    def getLong(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[Long] = None): Option[Long]

    def del(key: String): Option[String]

  }

}
