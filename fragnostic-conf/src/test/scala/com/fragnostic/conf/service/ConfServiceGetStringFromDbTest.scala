package com.fragnostic.conf.service

import com.fragnostic.conf.service.support.BaseConfTest

class ConfServiceGetStringFromDbTest extends BaseConfTest {

  val keyDb: String = "uno.dos.tres"

  describe("Conf Service Get String From Db Test") {

    it("Can Get Value As String from DB") {

      val opt = CakeConfService.confServiceApi.getString(key = keyDb) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be("123")
    }

  }
}
