package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetLongTest extends LifeCycleSupportCache {

  val cacheKeyOneLong: String = "uno.dos.tres.long"
  val longValue: Long = 123L
  val defaultValueLong: Option[Long] = Some(longValue)

  override def beforeAll(): Unit = {
    CakeConfService.confServiceApi.del(cacheKeyOneLong)
    CakeConfService.confServiceApi.del(cacheKeyTwo)
  }

  override def afterAll(): Unit = {
    CakeConfService.confServiceApi.del(cacheKeyOneLong)
    CakeConfService.confServiceApi.del(cacheKeyTwo)
  }

  describe("Conf Service Get Long Test") {

    ignore("Can Get Value As Long from Cache") {

      CakeConfService.confServiceApi.set(cacheKeyTwo, cacheValue)

      val opt = CakeConfService.confServiceApi.getLong(key = cacheKeyTwo) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(longValue)

    }

    ignore("Can Get Value As Long from Props") {

      val opt = CakeConfService.confServiceApi.getLong(key = cacheKeyOneLong) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(longValue)

    }

    ignore("Can Get Value As Long from Default") {

      val opt = CakeConfService.confServiceApi.getLong(key = cacheKeyTwo) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(longValue)

    }

    ignore("Can Not Get Value As Long From Key That Does Not Exists") {

      val opt = CakeConfService.confServiceApi.getLong(key = "ads.try.dvfdsfg.qwe") fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should be(None)

    }

  }

}

