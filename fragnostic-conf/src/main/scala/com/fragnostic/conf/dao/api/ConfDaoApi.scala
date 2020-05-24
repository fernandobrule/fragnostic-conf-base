package com.fragnostic.conf.dao.api

trait ConfDaoApi {

  def confCrud: ConfCrud

  trait ConfCrud {

    def getString(key: String): Either[String, Option[String]]

  }

}

