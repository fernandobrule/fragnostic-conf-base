package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.BaseConfTest

class ConfServiceTest extends BaseConfTest {

  val someInt: Int = 123
  val someLong: Long = 123456

  describe("Conf Service Test") {

    ignore("Can Validate") {
      CakeConfService.confService.validate(List("SOME_ENV_VAR", "Arwrarwrirwrarwro"))
        .contains("SOME_ENV_VAR") should be(true)
    }

    it("Can Get Conf") {
      val value = CakeConfService.confService.getString(key = "SOME_ENV_VAR") fold (
        error => throw new IllegalStateException(error),
        value => value)
      value should be("Arwrarwrirwrarwro")
    }

    it("Can Get Conf Like Ant Int") {
      val value = CakeConfService.confService.getInt("SOME_INT") fold (
        error => throw new IllegalStateException(error),
        value => value)
      value should be(someInt)
    }

    it("Can Get Conf Like A Long") {
      val value = CakeConfService.confService.getLong("SOME_LONG") fold (
        error => throw new IllegalStateException(error),
        value => value)
      value should be(someLong)
    }

  }

}
