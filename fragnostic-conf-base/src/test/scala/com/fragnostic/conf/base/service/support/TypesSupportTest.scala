package com.fragnostic.conf.base.service.support

import org.scalatest.funspec.AnyFunSpec

class TypesSupportTest extends AnyFunSpec with TypesSupport {

  describe("Types Support Test") {

    it("Can Get Booleam") {
      assertResult(toType(Some("true"), (a: String) => a.toBoolean) fold (
        error => false,
        opt => opt exists (myBoolean => myBoolean) //
      ))(true)
    }

    it("Can Get Long") {
      assertResult(toType(Some("123"), (a: String) => a.toLong) fold (
        error => 456L,
        opt => opt map (myLong => myLong) getOrElse 789L //
      ))(123L)
    }

    it("Can Get Short") {
      assertResult(toType(Some("123"), (a: String) => a.toShort) fold (
        error => 456,
        opt => opt map (myShort => myShort) getOrElse 789 //
      ))(123)
    }

    it("Can Get Int") {
      assertResult(toType(Some("123"), (a: String) => a.toInt) fold (
        error => 456,
        opt => opt map (myInt => myInt) getOrElse 789 //
      ))(123)
    }

    it("Can Get Int Alternative") {
      assertResult(toType(None, (a: String) => a.toInt) fold (
        error => 456,
        opt => opt map (myInt => myInt) getOrElse 789 //
      ))(789)
    }

    it("Can Get Int And Handle Error") {
      assertResult(toType(Some("qwerty"), (a: String) => a.toInt) fold (
        error => 456,
        opt => opt map (myInt => myInt) getOrElse 789 //
      ))(456)
    }

  }

}