package com.fragnostic.conf.service

import java.util.Locale

import com.fragnostic.conf.service.support.{ BaseConfTest, KeySupport }

class ConfServiceGetStringTest extends BaseConfTest with KeySupport {

  val keyEnv: String = "TEST_KEY_ENV"

  //
  // es/CL
  //
  val localeEsCl: Locale = new Locale.Builder().setRegion("CL").setLanguage("es").build()
  val keyEsCl: String = nextRandomKey
  val valueEsCl: String = nextRandomKey

  //
  // pt/BR
  //
  val localePtBr: Locale = new Locale.Builder().setRegion("BR").setLanguage("pt").build()
  val keyPtBr: String = nextRandomKey
  val valuePtBr: String = nextRandomKey

  //
  // en/US
  //
  val localeEnUs: Locale = new Locale.Builder().setRegion("US").setLanguage("en").build()
  val keyEnUs: String = nextRandomKey
  val valueEnUs: String = nextRandomKey

  override def beforeEach(): Unit = {
    CakeConfService.confServiceApi.set(keyEsCl, valueEsCl)
    CakeConfService.confServiceApi.set(i18nKey(localeEsCl, keyEsCl), valueEsCl)
    CakeConfService.confServiceApi.set(i18nKey(localePtBr, keyPtBr), valuePtBr)
    CakeConfService.confServiceApi.set(i18nKey(localeEnUs, keyEnUs), valueEnUs)
  }

  override def afterEach(): Unit = {
    CakeConfService.confServiceApi.del(keyEsCl)
    CakeConfService.confServiceApi.del(i18nKey(localeEsCl, keyEsCl))
    CakeConfService.confServiceApi.del(i18nKey(localePtBr, keyPtBr))
    CakeConfService.confServiceApi.del(i18nKey(localeEnUs, keyEnUs))
  }

  describe("Conf Service Get String Test") {

    it("Can Get Value As String from Cache") {

      val opt = CakeConfService.confServiceApi.getString(key = keyEsCl) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueEsCl)
    }

    it("Can Get Value As String es/CL from Cache") {

      val opt = CakeConfService.confServiceApi.getString(locale = Some(localeEsCl), key = keyEsCl) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueEsCl)
    }

    it("Can Get Value As String pt/BR from Cache") {

      val opt = CakeConfService.confServiceApi.getString(locale = Some(localePtBr), key = keyPtBr) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valuePtBr)
    }

    it("Can Get Value As String en/US from Cache") {

      val opt = CakeConfService.confServiceApi.getString(locale = Some(localeEnUs), key = keyEnUs) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueEnUs)
    }

    it("Can Get Value As String from Environment") {

      val opt = CakeConfService.confServiceApi.getString(key = keyEnv) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be("yep env")
    }

    it("Can Get Value As String es/CL from Properties") {

      val opt = CakeConfService.confServiceApi.getString(Some(localeEsCl), Option(this), keyUnoDosTres) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueUnoDosTresStringEsCl)
    }

    it("Can Get Value As String pt/BR from Properties") {

      val opt = CakeConfService.confServiceApi.getString(Some(localePtBr), Option(this), keyUnoDosTres) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueUnoDosTresStringPtBr)
    }

    it("Can Get Value As String en/US from Properties") {

      val opt = CakeConfService.confServiceApi.getString(Some(localeEnUs), Option(this), keyUnoDosTres) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueUnoDosTresStringEnUs)
    }

  }

}
