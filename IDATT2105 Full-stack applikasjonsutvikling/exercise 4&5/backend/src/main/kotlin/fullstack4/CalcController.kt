package fullstack4


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/calculator/")
class AuthorBookController{

    @PostMapping
    fun create( @RequestBody input: Input): ResponseEntity<*> {
        try {
            return ResponseEntity.status(HttpStatus.OK).body<Output>(Output(calc(input)))
        }catch (ex :ArithmeticException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ErrorResponse>(ErrorResponse("Can't divide by zero"))
        }

    }


    fun calc(input: Input): Int {

        when(input.sign){
            "+" -> return input.nr1 + input.nr2
            "-" -> return input.nr1 - input.nr2
            "*" -> return input.nr1 * input.nr2
            "/" -> return input.nr1 / input.nr2
        }
        return 0
    }
}