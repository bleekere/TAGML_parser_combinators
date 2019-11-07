package tag.parsercombinators

import lambdada.parsec.io.Reader
import lambdada.parsec.parser.*
import java.io.Serializable

// after: one parser after another, returning a list instead of a pair.
infix fun <I, A> Parser<I, A>.after(p: Parser<I, A>): Parser<I, List<A>> = this flatMap { a -> p map { listOf(a, it) } }

infix fun <I, A> Parser<I, List<A>>.after2(p: Parser<I, A>): Parser<I, List<A>> = this flatMap { a -> p map { a + it } }

fun openTagRecog(): Parser<Char, List<Char>> = char('[') thenRight contentTagRecog() thenLeft char('>')

fun closeTagRecog(): Parser<Char, List<Char>> = char('<') thenRight contentTagRecog() thenLeft char(']')

fun contentTagRecog(): Parser<Char, List<Char>> = (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep

fun openOptionalTagRecog(): Parser<Char, List<Char>> = string("[?") thenRight contentTagRecog() thenLeft char('>')

fun closeOptionalTagRecog(): Parser<Char, List<Char>> = string("<?") thenRight contentTagRecog() thenLeft char(']')

fun main() {
    val foo : Parser<Char, List<List<Char>>> = openTagRecog() after openOptionalTagRecog() after2 closeOptionalTagRecog() after2 closeTagRecog()
    val input = Reader.string("[root>[?del><?del]<root]")
    val foobar = foo(input)
    println(foobar)
}

