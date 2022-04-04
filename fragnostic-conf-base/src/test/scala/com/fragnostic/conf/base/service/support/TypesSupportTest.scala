package com.fragnostic.conf.base.service.support

import org.scalatest.funspec.AnyFunSpec

class TypesSupportTest extends AnyFunSpec with TypesSupport {

  describe("Types Support Test") {

    it("Can Get Booleam") {
      assertResult(toType("true", (a: String) => a.toBoolean) fold (
        error => false,
        myBoolean => myBoolean //
      ) //
      )(true)
    }

    it("Can Get Long") {
      assertResult(toType("123", (a: String) => a.toLong) fold (
        error => 456L,
        myLong => myLong //
      ) //
      )(123L)
    }

    it("Can Get Short") {
      assertResult(toType("123", (a: String) => a.toShort) fold (
        error => 456,
        myShort => myShort //
      ) //
      )(123)
    }

    it("Can Get Int") {
      assertResult(toType("123", (a: String) => a.toInt) fold (
        error => 456,
        myInt => myInt //
      ))(123)
    }

    it("Can Get Int Alternative") {
      assertResult(toType("", (a: String) => a.toInt) fold (
        error => error,
        myInt => myInt //
      ))("""For input string: """"")
    }

    it("Can Get Int And Handle Error") {
      assertResult(toType("qwerty", (a: String) => a.toInt) fold (
        error => error,
        myInt => myInt //
      ))("""For input string: "qwerty"""")
    }

  }

}