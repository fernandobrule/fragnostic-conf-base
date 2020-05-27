package com.fragnostic.conf.dao.impl

import com.fragnostic.conf.dao.api.CacheDaoApi
import org.slf4j.{ Logger, LoggerFactory }
import redis.clients.jedis.Jedis

import scala.util.Try

trait RedisDaoImpl extends CacheDaoApi with RedisConnectionAgnostic {

  def cacheCrud: CacheCrud = new RedisDaoImpl(jedis)

  class RedisDaoImpl(val jedis: Jedis) extends CacheCrud {

    private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

    override def set(key: String, value: String): Either[String, String] =
      Try(jedis.set(key, value)) fold (
        error => {
          logger.error(s"set() - error on set key[$key] with value[$value], ${error.getMessage}")
          Left(error.getMessage)
        },
        statusCodeReply => Right(statusCodeReply))

    override def set(key: String, value: Short): Either[String, String] = ???

    override def set(key: String, value: Int): Either[String, String] = ???

    override def set(key: String, value: Long): Either[String, String] = ???

    override def get(key: String): Either[String, Option[String]] =
      Try(jedis.get(key)) fold (
        error => {
          logger.error(s"get() - error on try to retrieve key[$key], ${error.getMessage}")
          Left(error.getMessage)
        },
        bulkReply => Right(Option(bulkReply)))

    override def del(key: String): Either[String, Option[String]] =
      get(key) fold (
        error => Left(error),
        opt => opt map (
          value => if (jedis.del(key) == 1) Right(Option(value)) else Left("redis.dao.del.error")) getOrElse Right(None))

    override def delAllKeys(): String =
      jedis.flushAll()

  }

}
