package com.fragnostic.conf.service.support

import org.scalatest.BeforeAndAfterAll

trait LifeCycleSupportCache extends BaseConfTest with BeforeAndAfterAll {

  protected val cacheValue = "123"
  protected val cacheKeyTwo: String = "one.two.three"

}
