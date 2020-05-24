package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceGetStringTest extends LifeCycleSupportCache {

  val cacheKeyOne: String = "uno.dos.tres"
  val cacheKeyNowhere: String = "one.two.three"
  val cacheKeyString: String = "the.numbers"
  val stringValueEsCl: String = "Uno, Dos y Tres"
  val stringValueEnUs: String = "One, Two and Three"
  val stringValuePtBr: String = "Um, Dois e Tr\u00eas"
  val defaultValueStringEsCl: Option[String] = Some(stringValueEsCl)
  val defaultValueStringEnUs: Option[String] = Some(stringValueEnUs)
  val defaultValueStringPtBr: Option[String] = Some(stringValuePtBr)

  override def beforeAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyNowhere)
  }

  override def afterAll(): Unit = {
    cacheService.del(cacheKeyOne)
    cacheService.del(cacheKeyNowhere)
  }

  describe("Conf Service Get As String Test") {

    it("Can Get Value As String es/CL from Cache") {

      cacheService.set(cacheKeyString, stringValueEsCl)

      val opt = cacheService.getString(localeEsCl, this, cacheKeyString, None)

      opt should not be None
      opt.get should be(stringValueEsCl)

    }

    it("Can Get Value As String pt/BR from Cache") {

      cacheService.set(cacheKeyString, stringValuePtBr)

      val opt = cacheService.getString(localeEsCl, this, cacheKeyString, None)

      opt should not be None
      opt.get should be(stringValuePtBr)

    }

    it("Can Get Value As String en/US from Cache") {

      cacheService.set(cacheKeyString, stringValueEnUs)

      val opt = cacheService.getString(localeEsCl, this, cacheKeyString, None)

      opt should not be None
      opt.get should be(stringValueEnUs)

    }

    it("Can Get Value As String es/CL from Props") {

      cacheService.del(cacheKeyString)
      val opt = cacheService.getString(localeEsCl, this, cacheKeyString, None)

      opt should not be None
      opt.get should be(stringValueEsCl)

    }

    it("Can Get Value As String en/US from Props") {

      cacheService.del(cacheKeyString)
      val opt = cacheService.getString(localeEnUs, this, cacheKeyString, None)

      opt should not be None
      opt.get should be(stringValueEnUs)

    }

    it("Can Get Value As String pt/BR from Props") {

      cacheService.del(cacheKeyString)
      val opt = cacheService.getString(localePtBr, this, cacheKeyString, None)

      opt should not be None
      opt.get should be(stringValuePtBr)

    }

    it("Can Get Value As String es/CL from Default") {

      val opt = cacheService.getString(localeEsCl, this, cacheKeyNowhere, defaultValueStringEsCl)

      opt should not be None
      opt.get should be(stringValueEsCl)

    }

    it("Can Get Value As String en/US from Default") {

      val opt = cacheService.getString(localeEsCl, this, cacheKeyNowhere, defaultValueStringEnUs)

      opt should not be None
      opt.get should be(stringValueEnUs)

    }

    it("Can Get Value As String pt/BR from Default") {

      val opt = cacheService.getString(localeEsCl, this, cacheKeyNowhere, defaultValueStringPtBr)

      opt should not be None
      opt.get should be(stringValuePtBr)

    }

    it("Can Not Get Value As String From Key That Does Not Exists") {

      val key = "ads.try.dvfdsfg.qwe"
      cacheService.getString(localeEsCl, this, key, None) should be(None)

    }

  }

}

