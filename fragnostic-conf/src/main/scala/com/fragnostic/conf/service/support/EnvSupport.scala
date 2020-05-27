package com.fragnostic.conf.service.support

trait EnvSupport {

  def getStringFromEnv(name: String): Option[String] =
    Option(System.getenv(name))

}
