package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class ConfServiceSetTest extends LifeCycleSupportCache {

  describe("Conf Service Set Test") {

    it("Can Set Value") {
      val key = "asjasjljl"
      val value = "EXTR"
      val message = cacheService.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

    it("Can Set Value Short") {
      val key = "asjasjljl.short"
      val value: Short = 123
      val message = cacheService.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

    it("Can Set Value Int") {
      val key = "asjasjljl.int"
      val value: Int = 123
      val message = cacheService.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

    it("Can Set Value Long") {
      val key = "asjasjljl.long"
      val value: Long = 123
      val message = cacheService.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

  }

}
