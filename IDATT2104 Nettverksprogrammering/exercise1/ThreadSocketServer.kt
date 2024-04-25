import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.util.*
import kotlin.concurrent.thread


fun main() {
    val PORTNR = 1250
    val readFromCommandLine = Scanner(System.`in`)
    println("How many threads should be ran")
    val nr: Int = readFromCommandLine.nextInt()
    val server = ServerSocket(PORTNR)
    for (i in 1..nr) {
        thread() {
            server.accept().use { socket ->
                BufferedReader(InputStreamReader(socket.getInputStream())).use { reader ->
                    PrintWriter(socket.getOutputStream(), true).use { writer ->
                        writer.println("You have contact with the server")
                        writer.println("Write a equation in the format 1 + 1, then i'll give you the answer,"
                                + "end with enter")
                        var aLine = reader.readLine()
                        while (aLine != null) {
                            val answer = calc(aLine.split(" ".toRegex()).toTypedArray())
                            writer.println(answer)
                            aLine = reader.readLine()
                        }
                    }
                }
            }
        }
    }
}
fun calc(array: Array<String>): String {
    if (array.size != 3) return "Wrong format"
    if (array[1] == "/" && array[2] == "0")return "You can't divide by zero"
    when(array[1]){
        "+" -> return (array[0].toDouble() + array[2].toDouble()).toString()
        "-" -> return (array[0].toDouble() - array[2].toDouble()).toString()
        "*" -> return (array[0].toDouble() * array[2].toDouble()).toString()
        "/" -> return (array[0].toDouble() / array[2].toDouble()).toString()
    }
    return "Wrong format"
}
