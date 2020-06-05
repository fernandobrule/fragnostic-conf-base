package com.fragnostic.conf.service.support

import java.util.Locale

trait KeySupport {

  def compose(localeOpt: Option[Locale], key: String): String =
    localeOpt map (locale => {
      s"${key}_${locale.getLanguage}_${locale.getCountry}"
    }) getOrElse key

}
