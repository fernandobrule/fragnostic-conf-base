package com.fragnostic.conf.service.support

import com.fragnostic.conf.dao.impl.RedisDaoImpl
import com.fragnostic.conf.service.impl.CacheServiceImpl
import org.scalatest.BeforeAndAfterAll
import redis.clients.jedis.Jedis

trait LifeCycleSupportCache extends BaseConfTest with BeforeAndAfterAll with JedisSupport {

  lazy val cacheServicePiece = new CacheServiceImpl with RedisDaoImpl {
    override val jedis: Jedis = jedisInstance
  }

  val cacheService = cacheServicePiece.cacheServiceApi

}
