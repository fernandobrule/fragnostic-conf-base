package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetAsLongTest extends LifeCycleSupportCache {

  val cacheKeyOne: String = "uno.dos.tres.long"
  val cacheKeyTwo: String = "one.two.three"
  val cacheValue = "123"
  val longValue: Long = 123L
  val defaultValueLong: Option[Long] = Some(longValue)

  override def beforeAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyTwo)
  }

  override def afterAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyTwo)
  }

  describe("Conf Service Get As Long Test") {

    it("Can Get Value As Long from Cache") {

      cacheService.set(cacheKeyTwo, cacheValue)

      val opt = cacheService.getLong(localeEsCl, this, cacheKeyTwo, None)

      opt should not be None
      opt.get should be(longValue)

    }

    it("Can Get Value As Long from Props") {

      val opt = cacheService.getLong(localeEsCl, this, cacheKeyOne, None)

      opt should not be None
      opt.get should be(longValue)

    }

    it("Can Get Value As Long from Default") {

      val opt = cacheService.getLong(localeEsCl, this, cacheKeyTwo, defaultValueLong)

      opt should not be None
      opt.get should be(longValue)

    }

    it("Can Not Get Value As Long From Key That Does Not Exists") {

      cacheService.getLong(localeEsCl, this, "ads.try.dvfdsfg.qwe", None) should be(None)

    }

  }

}

