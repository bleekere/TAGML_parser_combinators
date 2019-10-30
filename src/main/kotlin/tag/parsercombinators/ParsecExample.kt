package tag.parsercombinators

import lambdada.parsec.io.Reader
import lambdada.parsec.parser.*
import java.net.URL

fun main() {
    // de type van foo is een functie "Parser" en Parser zelf krijgt ook types mee:
    // Char en List (dat laatste heet "generics", m.a.w. je typeert een type)
    val foo: Parser<Char, List<List<Char>>> = (not(char(',')).rep).rep
    val input = Reader.string("hello, world!")
    // de waarde van foobar is de Response van de parser die aan foo is toegewezen;
    // de parser pakt de waarde van input
    val foobar = foo(input)
    // de parser functie geeft een Response
    // de Response heeft de subtypes Accept of Reject (-> data classes)
    println(foobar)

    when (foobar) {
        is Response.Accept -> println("good")
        is Response.Reject -> println("bad")
    }
    // good
}