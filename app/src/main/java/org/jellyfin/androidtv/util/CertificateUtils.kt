@file:JvmName("CertificateUtils")

package org.jellyfin.androidtv.util

import timber.log.Timber
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.util.Base64


fun deserializePEM(certificate: String): Certificate? {
	val certFactory = CertificateFactory.getInstance("X.509")
	try {
		return certFactory.generateCertificate(certificate.byteInputStream())
	} catch (e: CertificateException) {
		// TODO: Notify the user of error, maybe abort
		Timber.e(e, "Certificate could not be parsed.")
		return null
	}
}

fun serializePEM(certificate: Certificate): String {
	val contents = Base64.getMimeEncoder(64, "\n".toByteArray()).encodeToString(certificate.encoded).trim()
	return "-----BEGIN CERTIFICATE-----\n$contents\n-----END CERTIFICATE-----"
}
