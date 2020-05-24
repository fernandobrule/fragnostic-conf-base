package com.fragnostic.conf.dao

import com.fragnostic.conf.dao.support.DaoLifeCycleSupport

class DaoTest extends DaoLifeCycleSupport {

  describe("Dao Test") {

    it("Can Get String Conf") {

      val key: String = "uno.dos.tres"
      val value: String = "123"

      val opt: Option[String] = confCrud.getString(key) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(value)
    }
  }

}
