package tag.parsercombinators

import lambdada.parsec.parser.*
import lambdada.parsec.io.Reader

// legacy parsers
val anyCloseTagParser: Parser<Char, String> = char('<') thenRight charIn(CharRange('a', 'z')).rep thenLeft char(']') map {
    String(it.toCharArray())
}

val openAndCloseTagParser: Parser<Char, Pair<String, String>> = openTagParser then anyCloseTagParser

fun main() {
    val a = Reader.string("[root><root]")
    val r = anyOpenTagFollowedByTheExactSameCloseTagParser(a)
    println(r)
    val b = Reader.string("[root><wrong]")
    val s = anyOpenTagFollowedByTheExactSameCloseTagParser(b)
    println(s)
}