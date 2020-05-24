package com.fragnostic.conf.service

import com.fragnostic.conf.dao.impl.{ ConfDaoJdbcImpl, RedisDaoImpl }
import com.fragnostic.conf.service.api.ConfServiceApi
import com.fragnostic.conf.service.impl.ConfServiceImpl
import com.fragnostic.conf.service.support.JedisSupport
import com.fragnostic.dao.CakeDaoMySql
import javax.sql.DataSource
import redis.clients.jedis.Jedis

object CakeConfService extends JedisSupport {

  lazy val cakeDataSource: DataSource = CakeDaoMySql.mysql8DataSource.getDataSource fold (
    error => throw new IllegalStateException(error),
    dataSource => dataSource)

  lazy val confServicePiece: ConfServiceApi = new ConfServiceImpl with RedisDaoImpl with ConfDaoJdbcImpl {
    override val dataSource: DataSource = cakeDataSource
    override val jedis: Jedis = jedisInstance
  }

  val confService = confServicePiece.confService

}
