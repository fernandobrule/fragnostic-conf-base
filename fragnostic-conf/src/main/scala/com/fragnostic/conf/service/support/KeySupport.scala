package com.fragnostic.conf.service.support

import java.util.Locale

trait KeySupport {

  def i18nKey(locale: Locale, key: String): String = {
    s"${key}_${locale.getLanguage}_${locale.getCountry}"
  }

}
