package com.fragnostic.conf.base.service.support

import java.util.Locale

trait KeySupport {

  def compose(opt: Option[Locale], key: String): String =
    opt map (locale => {
      s"${key}_${locale.getLanguage}_${locale.getCountry}"
    }) getOrElse key

  def compose(tuple: (Long, Long), key: String): String =
    s"${tuple._1}.${tuple._2}.${key}"

  def compose(locale: Locale, tuple: (Long, Long), key: String): String =
    s"${compose(tuple, key)}_${locale.getLanguage}_${locale.getCountry}"

}
