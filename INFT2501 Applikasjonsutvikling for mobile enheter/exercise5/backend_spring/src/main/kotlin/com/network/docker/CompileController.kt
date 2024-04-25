package com.network.docker

import com.NTNU.FullStack.Controllers.cpp.Ans
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.FileWriter
import javax.validation.Valid

@RestController
@RequestMapping("/compile/")
class CompileController {
    @GetMapping
    fun getAll(): String = "Hello"

    @PostMapping
    fun create(@Valid @RequestBody code: Code): ResponseEntity<*> {
        //if the code isnt working and you get no response, try using the absolute path here
        val myWriter = FileWriter("./cpp/main.cpp")
        myWriter.write(code.code)
        myWriter.close()
        //And here
        val p = Runtime.getRuntime().exec("docker build ./cpp/ -t gcc")
        p.waitFor()
        val g = Runtime.getRuntime().exec("docker run --rm gcc")
        val a = String(g.inputStream.readAllBytes())
        return ResponseEntity.ok().body(Ans(a))
    }
}