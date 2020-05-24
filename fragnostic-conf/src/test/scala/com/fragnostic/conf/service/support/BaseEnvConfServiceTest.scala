package com.fragnostic.conf.service.support

import com.fragnostic.conf.service.api.EnvConfServiceApi
import com.fragnostic.conf.service.impl.EnvConfServiceImpl

trait BaseEnvConfServiceTest extends BaseConfTest {

  lazy val envConfServicePiece: EnvConfServiceApi = new EnvConfServiceImpl {}

  val envConfService = envConfServicePiece.envConfService

}
