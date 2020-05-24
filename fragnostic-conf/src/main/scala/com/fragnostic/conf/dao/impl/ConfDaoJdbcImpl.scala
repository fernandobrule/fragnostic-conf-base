package com.fragnostic.conf.dao.impl

import com.fragnostic.conf.dao.api.ConfDaoApi
import com.fragnostic.dao.crud._
import com.fragnostic.dao.support.{ ConnectionAgnostic, FilloutPreparedStatementAgnostic, ResultSetGetAgnostic }
import javax.sql.DataSource
import org.slf4j.{ Logger, LoggerFactory }

trait ConfDaoJdbcImpl extends ConfDaoApi with ConnectionAgnostic {

  def confCrud: ConfCrud = new ConfDaoJdbcImpl(dataSource)

  class ConfDaoJdbcImpl(val dataSource: DataSource) extends ConfCrud with ConfDaoSqlQueries
    with FindByIdAgnostic
    with FilloutPreparedStatementAgnostic
    with ResultSetGetAgnostic {

    private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

    override def getString(key: String): Either[String, Option[String]] =
      findById(key, sqlFindByKey, filloutPsWith1Args, resultSetGetString) fold (
        error => {
          logger.error(s"getString() - error al recuperar key[$key]: $error")
          Left(error)
        },
        value => Right(value))

  }

}

