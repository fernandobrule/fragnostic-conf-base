package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetShortTest extends LifeCycleSupportCache {

  val cacheKeyOneShort: String = "uno.dos.tres.short"
  val shortValue: Short = 123
  val defaultValueShort: Option[Short] = Some(shortValue)

  override def beforeAll(): Unit = {
    CakeConfService.confServiceApi.del(cacheKeyOneShort)
    CakeConfService.confServiceApi.del(cacheKeyTwo)
  }

  override def afterAll(): Unit = {
    CakeConfService.confServiceApi.del(cacheKeyOneShort)
    CakeConfService.confServiceApi.del(cacheKeyTwo)
  }

  describe("Conf Service Get Short Test") {

    it("Can Get Value As Short from Cache") {

      CakeConfService.confServiceApi.set(cacheKeyTwo, cacheValue)

      val opt = CakeConfService.confServiceApi.getShort(key = cacheKeyTwo) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(shortValue)

    }

    ignore("Can Get Value As Short from Props") {

      val opt = CakeConfService.confServiceApi.getShort(key = cacheKeyOneShort) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(shortValue)

    }

    ignore("Can Get Value As Short from Default") {

      val opt = CakeConfService.confServiceApi.getShort(key = cacheKeyTwo) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(shortValue)

    }

    ignore("Can Not Get Value As Short From Key That Does Not Exists") {

      val opt = CakeConfService.confServiceApi.getShort(key = "ads.try.dvfdsfg.qwe") fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should be(None)

    }

  }

}

