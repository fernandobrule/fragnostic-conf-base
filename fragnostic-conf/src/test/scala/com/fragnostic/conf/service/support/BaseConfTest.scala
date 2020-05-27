package com.fragnostic.conf.service.support

import java.util.UUID

import com.fragnostic.i18n.AbstractSingleMessageI18n
import org.scalatest.{ BeforeAndAfterAll, BeforeAndAfterEach, FunSpec, Matchers }

// dont import scala.language.postfixOps
import scala.language.postfixOps
// dont import scala.sys.process._
import scala.sys.process._

trait BaseConfTest extends FunSpec with Matchers with BeforeAndAfterEach with BeforeAndAfterAll with AbstractSingleMessageI18n {

  override def baseDir: String = "CONF_PROPS"

  override def baseName: String = "com.fragnostic.conf.fragnostic-conf"

  protected val keyUnoDosTres = "uno.dos.tres.string"
  protected val valueUnoDosTresStringEsCl = "Uno, Dos y Tres"
  protected val valueUnoDosTresStringPtBr = "Um, Dois e Tr\u00eas"
  protected val valueUnoDosTresStringEnUs = "One, Two and Three"
  protected val cacheKeyNowhere = "asdasdad"

  def nextRandomKey: String = UUID.randomUUID().toString.substring(0, 8)

  override def beforeAll(): Unit = {
    val ans: Int = "./fragnostic-conf/src/test/resources/beforeall/antbeforeall" !

    //
    println(s"ans:\n$ans\n")
    //
  }

  override def afterAll(): Unit = {
    //dataSource.close()
    val ans: Int = "./fragnostic-conf/src/test/resources/afterall/antafterall" !

    //
    println(s"ans:\n$ans\n")
    //
  }

}
