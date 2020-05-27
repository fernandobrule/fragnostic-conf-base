package com.fragnostic.conf.service

import com.fragnostic.conf.dao.impl.{ ConfDaoJdbcImpl, RedisDaoImpl }
import com.fragnostic.conf.service.api.ConfServiceApi
import com.fragnostic.conf.service.impl.ConfServiceImpl
import com.fragnostic.conf.service.support.{ EnvSupport, TypesSupport }
import com.fragnostic.dao.CakeDaoMySql
import javax.sql.DataSource
import redis.clients.jedis.Jedis

object CakeConfService extends EnvSupport with TypesSupport {

  val jedisInstance: Jedis = {

    val host: String = getStringFromEnv("REDIS_HOST") map (
      host => host) getOrElse {
        throw new IllegalStateException("jedis.support.get.host.error")
      }

    val port: Int = getStringFromEnv("REDIS_PORT") map (
      port => toInt(Option(port)) fold (
        error => throw new IllegalStateException("jedis.support.get.port.error"),
        opt => opt map (port => port) getOrElse {
          throw new IllegalStateException("jedis.support.get.port.error")
        })) getOrElse {
        throw new IllegalStateException("jedis.support.get.port.error")
      }

    new Jedis(host, port)
  }

  val cakeDataSource: DataSource = CakeDaoMySql.mysql8DataSource.getDataSource fold (
    error => throw new IllegalStateException(error),
    dataSource => dataSource)

  lazy val confServicePiece: ConfServiceApi = new ConfServiceImpl with RedisDaoImpl with ConfDaoJdbcImpl {
    override val dataSource: DataSource = cakeDataSource
    override val jedis: Jedis = jedisInstance
  }

  val confServiceApi = confServicePiece.confServiceApi

}
