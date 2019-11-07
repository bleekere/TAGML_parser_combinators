# TAGML Parser combinators

** Disclaimer: work in progress **

This is a small Kotlin experiment with buidling a context sensitive TAGML parser for genetically oriented TAGML, which means that the text is at times non-linear. 

There are two sides to a parser:

1. Recognizer: parses a document and checks whether the characters it encounters validate according to the provided grammar. In this case, a recognizer will check if the provided document is TAGML (based on the grammar rules you provide it with) and if the TAGML is well-formed (so whether all open tags are closed, etc).
For example: a text character in TAGML is grammatically defined as follows: `text ::= textCharacter*`. A recognizer for this would be `fun contentRecog(): Parser<Char, List<Char>> = (charIn(CharRange('a', 'z')) or charIn(CharRange('A','Z'))).rep`

2. Parser: after the recognition, the parser itself will build a data model (in our case: a hyper graph) based on certain rules. We could also have chosen to have the parser build an MCT data model, but we’re dealing with genetic tagging that includes nonlinearity which is difficult to express in an MCT.

The current experiment consists of (1) building recognisers for the content of TAGML files and (2) building a parser that builds a hypergraph model and maps the content to the model.
