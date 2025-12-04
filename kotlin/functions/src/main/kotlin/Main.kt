package org.example

//Unit corresponde ao any do typescript, inclusive o nada
fun printMessage(message: String): Unit{
    println(message)
}

//Diferente da primeira função, omiti o retorno.
//O Kotlin entende de maneira implicita que essa função retornará um Unit
//O segundo parametro, caso não receba uma informação, assume o valor padrão "Info"
fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    //Kotlin por padrão tem o conceito de interpolação de strings.
    //Logo, é possivel passar variaveis literais dentro da string, referenciando-as com o $
    //Isso evita operações de concatenação
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int{
    return x + y;
}

//esse é um exemplo de inline function, semelhante a arrow functions no ecmascript
fun multiply(x: Int, y: Int) = x * y;

fun main() {
    printMessage("Hello World")
    printMessageWithPrefix("Hello World")
    printMessageWithPrefix("Hello World", "Debug")

    //Kotlin é capaz de identificar os parametros independente da ordem
    //para isso, basta explicitar o nome do parametro seguido do sinal de igualdade e o valor do parametro
    printMessageWithPrefix(prefix = "LOG", message = "Hello World")

    println(sum(1,2))
    println(multiply(4,5))
}