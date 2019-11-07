package tag.parsercombinators

import lambdada.parsec.io.Reader
import lambdada.parsec.parser.*

// after: one parser after another, returning a list instead of a pair.
infix fun <I, A> Parser<I, A>.after(p: Parser<I, A>): Parser<I, List<A>> = this flatMap { a -> p map { listOf(a, it) } }

fun openTagRecog(): Parser<Char, List<Char>> = char('[') thenRight contentTagRecog() thenLeft char('>')

fun closeTagRecog(): Parser<Char, List<Char>> = char('<') thenRight contentTagRecog() thenLeft char(']')

fun contentTagRecog(): Parser<Char, List<Char>> = (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep

// fun closeTagRecog(): Parser<Char, Char> = char('')

fun main() {
    // val foo: Parser<Char, List<Char>> = openTagRecog() thenRight (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep
    val foo : Parser<Char, Pair<List<Char>,List<Char>>> = openTagRecog() then closeTagRecog()
    val input = Reader.string("[root><root]")
    val foobar = foo(input)
    println(foobar)

}

