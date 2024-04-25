import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.*

fun main() {
    val PORTNR = 1250
    val ReadFromCommandLine = Scanner(System.`in`)
    print("Give the name of the host: ")
    val serverName = ReadFromCommandLine.nextLine()
    Socket(serverName, PORTNR).use{socket ->
        println("Connection created.")
        PrintWriter(socket.getOutputStream(), true).use{writer ->
            BufferedReader(InputStreamReader(socket.getInputStream())).use{reader ->
                println(reader.readLine())
                println(reader.readLine())
                var aline = ReadFromCommandLine.nextLine()
                while (aline != "") {
                    writer.println(aline)
                    val respons = reader.readLine()
                    println("From server: $respons")
                    aline = ReadFromCommandLine.nextLine()

                }
            }
        }
    }
}