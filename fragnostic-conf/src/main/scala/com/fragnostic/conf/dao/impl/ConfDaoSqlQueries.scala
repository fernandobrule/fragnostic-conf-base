package com.fragnostic.conf.dao.impl

trait ConfDaoSqlQueries {

  protected val sqlFindByKey: String =
    """
      |
      | select frg_conf_value
      | from fragnostic_conf_db_test.fragnostic_conf
      | where frg_conf_key = ?
      |
      |""".stripMargin

}
