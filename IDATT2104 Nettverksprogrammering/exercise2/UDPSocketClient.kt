package exercise2

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*


fun main() {
    val sc = Scanner(System.`in`)
    val ds = DatagramSocket()
    val ip = InetAddress.getLocalHost()
    var receive = ByteArray(65535)
    var msg = sc.nextLine()
    while (msg != "") {
        val buf: ByteArray = msg.toByteArray()
        val dpSend = DatagramPacket(buf, buf.size, ip, 1250)
        ds.send(dpSend)
        val dpReceive = DatagramPacket(receive, receive.size, ip, 1250)
        ds.receive(dpReceive)
        println(String(receive))
        receive = ByteArray(65535)
        msg = sc.nextLine()
    }
}
