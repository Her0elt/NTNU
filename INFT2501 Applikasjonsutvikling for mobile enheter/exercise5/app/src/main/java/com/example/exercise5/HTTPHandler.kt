package com.example.exercise5.Components


import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.*

enum class HTTP { GET, POST, GET_WITH_HEADER }
const val ENCODING = "UTF-8"
class HTTPHandler(private val URL: String) {
    init {
        CookieHandler.setDefault(
            CookieManager(null,
                CookiePolicy.ACCEPT_ALL)
        )
    }
    private fun openConnection(url: String): URLConnection {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.setRequestProperty("Accept-Charset", ENCODING)
        return connection
    }
    fun get(parameterList: Map<String, String>): String {
        val connection = openConnection(URL +
                encodeParameters(parameterList))
        connection.inputStream.use { response ->
            return readResponseBody(response, getCharSet(connection))
        }
    }
    fun post(parameterList: Map<String, String>): String {
        val connection = openConnection(URL)
        connection.doOutput = true
        val outputType = "application/x-www-form-urlencoded;charset=$ENCODING"
        connection.setRequestProperty("Content-Type", outputType)
        connection.outputStream.use { outputStream ->
            val postString = encodeParameters(parameterList)
            outputStream.write(postString.toByteArray(charset(ENCODING)))
        }
        connection.inputStream.use { inputStream ->
            return readResponseBody(inputStream, getCharSet(connection))
        }
    }
    fun getWithHeader(parameterList: Map<String, String>): String {
        val connection = openConnection(URL +
                encodeParameters(parameterList))
        var response = ""
        for ((key, value) in connection.headerFields) response +=
            "$key=$value\n"
        connection.inputStream.use { inputStream ->
            response += readResponseBody(inputStream,
                getCharSet(connection))
        }
        return response
    }
    private fun encodeParameters(parameterList: Map<String, String>):
            String {
        var parameterString = "?"
        for ((key, value) in parameterList) {
            try {
                parameterString += URLEncoder.encode(key, ENCODING)
                parameterString += "="
                parameterString += URLEncoder.encode(value, ENCODING)
                parameterString += "&"
            } catch (e: UnsupportedEncodingException) {
            }
        }
        return parameterString
    }
    private fun readResponseBody(inputStream: InputStream, charset:
    String?): String {
        var body = ""
        try {
            BufferedReader(InputStreamReader(inputStream, charset)).use {
                    bufferedReader ->
                var lines = 0;
                while (true) {
                    val line = bufferedReader.readLine()
                    if (line != null) {
                        if (lines > 0) {
                            body += "\n"
                        }
                        body += line
                    } else {
                        break
                    }
                    lines++
                }
            }
        } catch (e: Exception) {
            body += "******* Problem reading from server *******\n$e"
        }
        return body
    }
    private fun getCharSet(connection: URLConnection): String? {
        var charset: String? = ENCODING
        val contentType = connection.contentType
        val contentInfo = contentType.replace(" ",
            "").split(";").toTypedArray()
        for (param in contentInfo) {
            if (param.startsWith("charset=")) charset =
                param.split("=").toTypedArray()[1]
        }
        return charset
    }
}
