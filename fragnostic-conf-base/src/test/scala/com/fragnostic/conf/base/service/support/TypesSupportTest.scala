package com.fragnostic.conf.base.service.support

import org.scalatest.{ FunSpec, Matchers }

class TypesSupportTest extends FunSpec with Matchers with TypesSupport {

  describe("Types Support Test") {

    it("Can Get Booleam") {
      toType(Some("true"), (a: String) => a.toBoolean) fold (
        error => false,
        opt => opt map (myBoolean => myBoolean) getOrElse false) should be(true)
    }

    it("Can Get Long") {
      toType(Some("123"), (a: String) => a.toLong) fold (
        error => 456L,
        opt => opt map (myLong => myLong) getOrElse 789L) should be(123L)
    }

    it("Can Get Short") {
      toType(Some("123"), (a: String) => a.toShort) fold (
        error => 456,
        opt => opt map (myShort => myShort) getOrElse 789) should be(123)
    }

    it("Can Get Int") {
      toType(Some("123"), (a: String) => a.toInt) fold (
        error => 456,
        opt => opt map (myInt => myInt) getOrElse 789) should be(123)
    }

    it("Can Get Int Alternative") {
      toType(None, (a: String) => a.toInt) fold (
        error => 456,
        opt => opt map (myInt => myInt) getOrElse 789) should be(789)
    }

    it("Can Get Int And Handle Error") {
      toType(Some("qwerty"), (a: String) => a.toInt) fold (
        error => 456,
        opt => opt map (myInt => myInt) getOrElse 789) should be(456)
    }

  }

}