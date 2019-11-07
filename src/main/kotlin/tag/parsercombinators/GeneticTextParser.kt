package tag.parsercombinators

import lambdada.parsec.io.Reader
import lambdada.parsec.parser.*

fun openTagRecog(): Parser<Char, List<Char>> = char('[') thenRight contentTagRecog() thenLeft char('>')

fun contentTagRecog(): Parser<Char, List<Char>> = (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep

// fun closeTagRecog(): Parser<Char, Char> = char('')

fun main() {
    // val foo: Parser<Char, List<Char>> = openTagRecog() thenRight (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep
    val foo : Parser<Char, List<Char>> = openTagRecog()
    val input = Reader.string("[root>")
    val foobar = foo(input)
    println(foobar)

}

