package com.fragnostic.conf.service.impl

import com.fragnostic.conf.service.api.EnvConfServiceApi
import org.slf4j.{ Logger, LoggerFactory }

trait EnvConfServiceImpl extends EnvConfServiceApi {

  def envConfService = new DefaultEnvConfService

  class DefaultEnvConfService extends EnvConfServiceApi {

    private def logger: Logger = LoggerFactory.getLogger(getClass.getName)

    private def getEnvProp(name: String): Option[String] =
      Option(System.getenv(name))

    private def valida(enVar: String): (String, String) =
      getEnvProp(enVar).map(value => (enVar, value)) getOrElse ((enVar, "FAIL"): (String, String))

    private def valida(enVars: List[String]): Map[String, String] =
      if (enVars.nonEmpty) {
        val tuple = valida(enVars.head)
        valida(enVars.tail) + (tuple._1 -> tuple._2)
      } else {
        Map[String, String]()
      }

    override def validate(enVars: List[String]): Map[String, String] =
      valida(enVars)

    override def getConf(key: String): Either[String, String] =
      getEnvProp(key) map (value => Right(value)) getOrElse {
        logger.error(s"getConf() - value na for key:$key")
        Left("get.conf.error")
      }

    override def getConfLikeAnInt(key: String): Either[String, Int] =
      getEnvProp(key) map (
        value => try {
          Right(value.toInt)
        } catch {
          case e: NumberFormatException =>
            logger.error(s"getConfLikeAnInt() - $e")
            Left("get.conf.like.an.int.error")
        }) getOrElse {
          logger.error(s"getConfLikeAnInt() - value na for key:$key")
          Left("get.conf.like.an.int.error")
        }

    override def getConfLikeALong(key: String): Either[String, Long] =
      getEnvProp(key) map (
        value => try {
          Right(value.toLong)
        } catch {
          case e: NumberFormatException =>
            logger.error(s"getConfLikeALong() - $e")
            Left("get.conf.like.an.int.error")
        }) getOrElse {
          logger.error(s"getConfLikeALong() - value na for key:$key")
          Left("get.conf.like.an.int.error")
        }

  }

}
