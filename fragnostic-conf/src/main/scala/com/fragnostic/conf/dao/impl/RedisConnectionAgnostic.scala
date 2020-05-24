package com.fragnostic.conf.dao.impl

import redis.clients.jedis.Jedis

trait RedisConnectionAgnostic {

  val jedis: Jedis

}
