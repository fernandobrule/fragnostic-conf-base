package com.fragnostic.conf.service.api

trait EnvConfServiceApi {

  def envConfService: EnvConfServiceApi

  trait EnvConfServiceApi {

    def validate(vars: List[String]): Map[String, String]

    def getConf(name: String): Either[String, String]

    def getConfLikeAnInt(name: String): Either[String, Int]

    def getConfLikeALong(name: String): Either[String, Long]

  }

}
