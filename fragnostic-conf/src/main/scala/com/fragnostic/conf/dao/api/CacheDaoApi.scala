package com.fragnostic.conf.dao.api

trait CacheDaoApi {

  def cacheCrud: CacheCrud

  trait CacheCrud {

    def set(key: String, value: String): Either[String, String]

    def set(key: String, value: Short): Either[String, String]

    def set(key: String, value: Int): Either[String, String]

    def set(key: String, value: Long): Either[String, String]

    def get(key: String): Either[String, Option[String]]

    def del(key: String): Either[String, Option[String]]

    def delAllKeys(): String

  }

}
