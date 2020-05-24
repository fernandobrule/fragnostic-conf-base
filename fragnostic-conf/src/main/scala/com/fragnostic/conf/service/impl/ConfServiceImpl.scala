package com.fragnostic.conf.service.impl

import java.util.Locale

import com.fragnostic.conf.dao.api.{ CacheDaoApi, ConfDaoApi }
import com.fragnostic.conf.service.api.ConfServiceApi
import com.fragnostic.i18n.api.ResourceI18n

/**
 * Configuration from Cache -> Environment -> Properties -> DB
 */
trait ConfServiceImpl extends ConfServiceApi {
  this: CacheDaoApi with ConfDaoApi =>

  def confService = new DefaultConfService

  class DefaultConfService extends ConfServiceApi {

    private def getEnvProp(name: String): Option[String] =
      Option(System.getenv(name))

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

    override def validate(vars: List[String]): Map[String, String] = {
      // TODO
      Map[String, String]()
    }

    override def getString(locale: Option[Locale], props: Option[ResourceI18n], key: String): Either[String, Option[String]] =
      //
      // from cache
      cacheCrud.get(key) map (
        //
        // if not, from env
        value => Right(Option(value))) getOrElse {
          //
          // if not, from props
          getEnvProp(key) map (value => Right(Option(value))) getOrElse {
            locale map (locale =>
              props map (i18n =>
                getStringFromProps(locale, i18n, key) map (
                  //
                  // if not, get from db
                  value => Right(Option(value))) getOrElse {
                    getStringFromDb(key)
                  } //
              ) getOrElse {
                //
                // if not, get from db
                getStringFromDb(key)
              } //
            ) getOrElse {
              //
              // if not, get from db
              getStringFromDb(key)
            }
          }
        }

    override def getInt(name: String): Either[String, Int] = {
      Left("not yet")
    }

    override def getLong(str: String): Either[String, Int] = {
      Left("not yet")
    }

  }

}
