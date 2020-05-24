package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetAsShortTest extends LifeCycleSupportCache {

  val cacheKeyOne: String = "uno.dos.tres.short"
  val cacheKeyTwo: String = "one.two.three"
  val cacheValue = "123"
  val intValue: Short = 123
  val defaultValueShort: Option[Short] = Some(intValue)

  override def beforeAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyTwo)
  }

  override def afterAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyTwo)
  }

  describe("Conf Service Get As Short Test") {

    it("Can Get Value As Short from Cache") {

      cacheService.set(cacheKeyTwo, cacheValue)

      val opt = cacheService.getShort(localeEsCl, this, cacheKeyTwo, None)

      opt should not be None
      opt.get should be(intValue)

    }

    it("Can Get Value As Short from Props") {

      val opt = cacheService.getShort(localeEsCl, this, cacheKeyOne, None)

      opt should not be None
      opt.get should be(intValue)

    }

    it("Can Get Value As Short from Default") {

      val opt = cacheService.getShort(localeEsCl, this, cacheKeyTwo, defaultValueShort)

      opt should not be None
      opt.get should be(intValue)

    }

    it("Can Not Get Value As Short From Key That Does Not Exists") {

      cacheService.getShort(localeEsCl, this, "ads.try.dvfdsfg.qwe", None) should be(None)

    }

  }

}

