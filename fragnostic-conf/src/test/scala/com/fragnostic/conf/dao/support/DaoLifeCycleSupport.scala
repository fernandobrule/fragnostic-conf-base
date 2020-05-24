package com.fragnostic.conf.dao.support

import com.fragnostic.conf.dao.impl.ConfDaoJdbcImpl
import com.fragnostic.dao.CakeDaoMySql
import com.mysql.cj.jdbc.MysqlDataSource
import org.scalatest.{ BeforeAndAfterAll, FunSpec, Matchers }

// dont import scala.language.postfixOps
import scala.language.postfixOps
// dont import scala.sys.process._
import scala.sys.process._

trait DaoLifeCycleSupport extends FunSpec with Matchers with BeforeAndAfterAll
  with ConfDaoJdbcImpl {

  val dataSource: MysqlDataSource = CakeDaoMySql.mysql8DataSource.getDataSource fold (
    error => throw new IllegalStateException(error),
    dataSource => dataSource)

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
