package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetAsIntTest extends LifeCycleSupportCache {

  val cacheKeyOne: String = "uno.dos.tres.int"
  val cacheKeyTwo: String = "one.two.three"
  val cacheValue = "123"
  val intValue: Int = 123
  val defaultValueInt: Option[Int] = Some(intValue)

  override def beforeAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyTwo)
  }

  override def afterAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyTwo)
  }

  describe("Conf Service Get As Int Test") {

    it("Can Get Value As Int from Cache") {

      cacheService.set(cacheKeyTwo, cacheValue)

      val opt = cacheService.getInt(localeEsCl, this, cacheKeyTwo, None)

      opt should not be None
      opt.get should be(intValue)

    }

    it("Can Get Value As Int from Props") {

      val opt = cacheService.getInt(localeEsCl, this, cacheKeyOne, None)

      opt should not be None
      opt.get should be(intValue)

    }

    it("Can Get Value As Int from Default") {

      val opt = cacheService.getInt(localeEsCl, this, cacheKeyTwo, defaultValueInt)

      opt should not be None
      opt.get should be(intValue)

    }

    it("Can Not Get Value As Int From Key That Does Not Exists") {

      cacheService.getInt(localeEsCl, this, "ads.try.dvfdsfg.qwe", None) should be(None)

    }

  }

}

