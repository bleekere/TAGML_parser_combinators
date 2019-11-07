package tag.parsercombinators

import lambdada.parsec.parser.*


val openTagParser: Parser<Char, String> = char('[') thenRight charIn(CharRange('a', 'z')).rep thenLeft char('>') map {
    String(it.toCharArray())
}

fun expectedCloseTagParser(expected: String): Parser<Char, String> = char('<') thenRight string(expected) thenLeft char(']')
// it is possible to make a similar function of the openTagParser value as well

val anyOpenTagFollowedByTheExactSameCloseTagParser: Parser<Char, String> = {
    when (val result = openTagParser(it)) {
        is Response.Accept -> expectedCloseTagParser(result.value)(result.input)
        is Response.Reject -> result
    }
}