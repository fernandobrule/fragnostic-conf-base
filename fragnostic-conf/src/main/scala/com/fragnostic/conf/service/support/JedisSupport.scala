package com.fragnostic.conf.service.support

import com.fragnostic.conf.service.CakeConfService
import org.slf4j.{ Logger, LoggerFactory }
import redis.clients.jedis.Jedis

trait JedisSupport {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  private def getHost: String = CakeConfService.confService.getString(key = "REDIS_HOST") fold (
    error => throw new IllegalStateException(error),
    opt => opt map (
      host => host) getOrElse {
        throw new IllegalStateException("jedis.support.get.host.error.host.not.available")
      })

  private def getPort: Int = CakeConfService.confService.getInt(name = "REDIS_PORT") fold (
    error => {
      logger.error(s"jedisInstance - error:$error")
      throw new IllegalStateException(error)
    },
    port => {
      logger.info(s"jedisInstance - port:$port")
      port
    })

  protected val jedisInstance: Jedis =
    new Jedis(getHost, getPort)

}
