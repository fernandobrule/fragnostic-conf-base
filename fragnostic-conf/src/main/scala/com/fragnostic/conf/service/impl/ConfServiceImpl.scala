package com.fragnostic.conf.service.impl

import java.util.Locale

import com.fragnostic.conf.dao.api.{ CacheDaoApi, ConfDaoApi }
import com.fragnostic.conf.service.api.ConfServiceApi
import com.fragnostic.conf.service.support.{ EnvSupport, KeySupport, TypesSupport }
import com.fragnostic.i18n.api.ResourceI18n

/**
 * Configuration from Cache -> Environment -> Properties -> DB
 */
trait ConfServiceImpl extends ConfServiceApi {
  this: CacheDaoApi with ConfDaoApi =>

  def confServiceApi = new DefaultConfService

  class DefaultConfService extends ConfServiceApi with EnvSupport with TypesSupport with KeySupport {

    private def getStringFromDb(key: String): Either[String, Option[String]] =
      confCrud.getString(key) fold (
        error => Left("conf.service.get.string.from.db.error"),
        opt => Right(opt))

    private def getStringFromProps(locale: Locale, i18n: ResourceI18n, key: String): Option[String] = {
      val value: String = i18n.getString(locale, key)
      if (key != value) {
        Some(value)
      } else {
        None
      }
    }

    private def compose(localeOpt: Option[Locale], key: String): String =
      localeOpt map (locale => { i18nKey(locale, key) }) getOrElse key

    private def envPropsDb(locale: Option[Locale], props: Option[ResourceI18n], key: String): Either[String, Option[String]] = {
      // get from env
      getStringFromEnv(key) map ( //
        value => Right(Option(value))) getOrElse {
          locale map (locale =>
            props map (i18n =>
              // get from props
              getStringFromProps(locale, i18n, key) map (
                value => Right(Option(value)) //
              ) getOrElse {
                  // get from db
                  getStringFromDb(key)
                } //
            ) getOrElse {
              // get from db
              getStringFromDb(key)
            } //
          ) getOrElse {
            // get from db
            getStringFromDb(key)
          }
        }
    }

    override def validate(vars: List[String]): Map[String, String] = {
      // TODO
      Map[String, String]()
    }

    override def set(key: String, value: String): Either[String, String] =
      cacheCrud.set(key, value) fold (
        error => Left("conf.service.set.error"),
        statusCodeReply =>
          if ("OK" == statusCodeReply) {
            Right("conf.service.set.success")
          } else {
            Left("conf.service.set.error")
          })

    override def set(key: String, value: Short): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: Int): Either[String, String] =
      set(key, value.toString)

    override def set(key: String, value: Long): Either[String, String] =
      set(key, value.toString)

    // cache -> environment -> properties -> db
    override def getString(locale: Option[Locale], props: Option[ResourceI18n], key: String): Either[String, Option[String]] =
      // get from cache
      cacheCrud.get(compose(locale, key)) fold (
        error => {
          envPropsDb(locale, props, key)
        },
        opt => opt map ( //
          value => Right(Option(value)) //
        ) getOrElse {
            envPropsDb(locale, props, key)
          })

    override def getShort(props: Option[ResourceI18n], key: String): Either[String, Option[Short]] =
      getString(props = props, key = key) fold (
        error => Left(error),
        opt => toShort(opt))

    override def getInt(props: Option[ResourceI18n] = None, key: String): Either[String, Option[Int]] =
      getString(props = props, key = key) fold (
        error => Left(error),
        opt => toInt(opt))

    override def getLong(props: Option[ResourceI18n] = None, key: String): Either[String, Option[Long]] =
      getString(props = props, key = key) fold (
        error => Left(error),
        opt => toLong(opt))

    override def del(key: String): Either[String, Option[String]] =
      cacheCrud.del(key)

    override def delAllKeys(): Either[String, String] =
      if ("1" == cacheCrud.delAllKeys()) Right("conf.service.del.all.keys.success")
      else Left("conf.service.del.all.keys.error")
  }

}
