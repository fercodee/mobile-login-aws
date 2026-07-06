package com.example.mobilelogin.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SmsService {
    private val logger = LoggerFactory.getLogger(SmsService::class.java)

    fun sendSms(phone: String, code: String) {
        // TODO: Integrate with AWS SNS later
        logger.info("=========================================")
        logger.info("SENDING SMS TO: $phone")
        logger.info("YOUR CONFIRMATION CODE IS: $code")
        logger.info("=========================================")
    }
}
