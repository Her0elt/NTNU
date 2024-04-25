package exercise1

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

fun main() {
    val PORTNR = 1250
    val server = ServerSocket(PORTNR)
    println("Wating for connection")
    server.accept().use{socket ->
        BufferedReader(InputStreamReader(socket.getInputStream())).use{ reader ->
            PrintWriter(socket.getOutputStream(), true).use{writer ->
                var headers = ""
                var line = reader.readLine()
                while (line != "") {
                    headers += "<li>$line</li>\n"
                    line = reader.readLine()
                }
                writer.println("HTTP/1.0 200 OK")
                writer.println("Content-Type: text/html; charset=utf-8")
                writer.println("")
                writer.println("<!DOCTYPE html><html><body>")
                writer.println("<h1> U have connect to the host </h1>")
                writer.println("<ul>")
                writer.println(headers)
                writer.println("</ul>")
                writer.println("</body></html>")
                writer.flush()
            }
        }
    }
    server.close()
}
