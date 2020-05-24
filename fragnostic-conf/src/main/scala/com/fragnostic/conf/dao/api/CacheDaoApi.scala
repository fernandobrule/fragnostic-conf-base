package com.fragnostic.conf.dao.api

trait CacheDaoApi {

  def cacheCrud: CacheCrud

  trait CacheCrud {

    def set(key: String, value: String): Either[String, String]

    def get(key: String): Option[String]

    def del(key: String): Option[String]

  }

}
