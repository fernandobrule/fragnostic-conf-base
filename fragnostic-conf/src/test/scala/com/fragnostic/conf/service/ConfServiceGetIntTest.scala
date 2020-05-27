package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetIntTest extends LifeCycleSupportCache {

  val cacheKeyOneInt: String = "uno.dos.tres.int"
  val intValue: Int = 123
  val defaultValueInt: Option[Int] = Some(intValue)

  override def beforeAll(): Unit = {
    CakeConfService.confServiceApi.del(cacheKeyOneInt)
    CakeConfService.confServiceApi.del(cacheKeyTwo)
  }

  override def afterAll(): Unit = {
    CakeConfService.confServiceApi.del(cacheKeyOneInt)
    CakeConfService.confServiceApi.del(cacheKeyTwo)
  }

  describe("Conf Service Get Int Test") {

    ignore("Can Get Value As Int from Cache") {

      CakeConfService.confServiceApi.set(cacheKeyTwo, cacheValue)

      val opt = CakeConfService.confServiceApi.getInt(key = cacheKeyTwo) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(intValue)

    }

    ignore("Can Get Value As Int from Props") {

      val opt = CakeConfService.confServiceApi.getInt(key = cacheKeyOneInt) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(intValue)

    }

    ignore("Can Get Value As Int from Default") {

      val opt = CakeConfService.confServiceApi.getInt(key = cacheKeyTwo) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(intValue)

    }

    ignore("Can Not Get Value As Int From Key That Does Not Exists") {

      val opt = CakeConfService.confServiceApi.getInt(key = "ads.try.dvfdsfg.qwe") fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should be(None)

    }

  }

}

