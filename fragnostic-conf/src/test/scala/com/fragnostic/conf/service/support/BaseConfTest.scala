package com.fragnostic.conf.service.support

import java.util.Locale

import com.fragnostic.i18n.AbstractSingleMessageI18n
import org.scalatest.{ FunSpec, Matchers }

trait BaseConfTest extends FunSpec with Matchers with AbstractSingleMessageI18n {

  override def baseDir: String = "CONF_PROPS"

  override def baseName: String = "cl.atacamasoft.fp.conf.service.futurepyme-conf"

  protected val localeEsCl: Locale =
    new Locale.Builder().setRegion("CL").setLanguage("es").build()
  protected val localeEnUs: Locale =
    new Locale.Builder().setRegion("US").setLanguage("en").build()
  protected val localePtBr: Locale =
    new Locale.Builder().setRegion("BR").setLanguage("pt").build()

}
