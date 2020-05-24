package com.fragnostic.conf.service.impl

import java.util.Locale

import com.fragnostic.conf.dao.api.CacheDaoApi
import com.fragnostic.conf.service.api.CacheServiceApi
import com.fragnostic.i18n.api.ResourceI18n

import scala.util.Try

class CacheServiceImpl extends CacheServiceApi {
  this: CacheDaoApi =>

  def cacheServiceApi = new DefaultCacheService

  class DefaultCacheService extends CacheServiceApi {

    override def set(key: String, value: Long): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: Int): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: Short): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: String): Either[String, String] =
      cacheCrud.set(key, value) fold (
        error => Left("conf.service.set.error"),
        statusCodeReply =>
          if ("OK" == statusCodeReply) {
            Right("conf.service.set.success")
          } else {
            Left("conf.service.set.error")
          })

    private def toShort(value: String): Option[Short] =
      Try(value.toShort) fold (
        error => None,
        value => Some(value))

    private def toInt(value: String): Option[Int] =
      Try(value.toInt) fold (
        error => None,
        value => Some(value))

    private def toLong(value: String): Option[Long] =
      Try(value.toLong) fold (
        error => None,
        value => Some(value))

    private def getStringFromProps(locale: Locale, i18n: ResourceI18n, key: String): Option[String] = {
      val value: String = i18n.getString(locale, key)
      if (key != value) {
        set(key, value)
        Some(value)
      } else {
        None
      }
    }

    override def getString(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[String] = None): Option[String] =
      cacheCrud.get(key) map (
        value => Option(value)) getOrElse {
          getStringFromProps(locale, props, key) map (value => Option(value)) getOrElse {
            defaultValue
          }
        }

    override def getShort(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[Short] = None): Option[Short] =
      cacheCrud.get(key) map (
        value => toShort(value)) getOrElse {
          getStringFromProps(locale, props, key) map (value => toShort(value)) getOrElse defaultValue
        }

    override def getInt(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[Int] = None): Option[Int] =
      cacheCrud.get(key) map (
        value => toInt(value)) getOrElse {
          getStringFromProps(locale, props, key) map (value => toInt(value)) getOrElse defaultValue
        }

    override def getLong(locale: Locale, props: ResourceI18n, key: String, defaultValue: Option[Long]): Option[Long] =
      cacheCrud.get(key) map (
        value => toLong(value)) getOrElse {
          getStringFromProps(locale, props, key) map (value => toLong(value)) getOrElse defaultValue
        }

    override def del(key: String): Option[String] =
      cacheCrud.del(key)

  }

}

