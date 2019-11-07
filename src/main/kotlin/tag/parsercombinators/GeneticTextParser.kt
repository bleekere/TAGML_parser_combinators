package tag.parsercombinators

import lambdada.parsec.io.Reader
import lambdada.parsec.parser.*

fun main() {
    val foo: Parser<Char, List<Char>> = char('[') thenRight (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep thenLeft char('>')
    val input = Reader.string("[root>")
    val foobar = foo(input)
    println(foobar)

}