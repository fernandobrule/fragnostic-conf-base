package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.LifeCycleSupportCache

class CacheCrudSetTest extends LifeCycleSupportCache {

  describe("Conf Service Set Test") {

    ignore("Can Set Value") {
      val key = "asjasjljl"
      val value = "EXTR"
      val message = CakeConfService.confServiceApi.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

    ignore("Can Set Value Short") {
      val key = "asjasjljl.short"
      val value: Short = 123
      val message = CakeConfService.confServiceApi.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

    ignore("Can Set Value Int") {
      val key = "asjasjljl.int"
      val value: Int = 123
      val message = CakeConfService.confServiceApi.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

    ignore("Can Set Value Long") {
      val key = "asjasjljl.long"
      val value: Long = 123
      val message = CakeConfService.confServiceApi.set(key, value) fold (
        error => throw new IllegalStateException(error),
        message => message)

      message should be("conf.service.set.success")
    }

  }

}
