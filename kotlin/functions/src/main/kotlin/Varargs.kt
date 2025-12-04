package org.example

fun main() {
    // O Kotlin tem a possibilidade de declarar funcões dentro do escopo de outras funçoes, semelhante ao javascript
    // Essa é a forma de declaração de varargs em kotlin
    fun printAll(vararg messages: String) {
        for(message in messages) println(message);
    }
    printAll("Ysmael", "Marinho")

    fun printAllWithPrefix(vararg messages: String, prefix: String) {
        //Além da interpolação de strings, é possivel fazer concatenação
        for(m in messages) println(prefix + m);
    }

    // Quando se tem argumentos que não fazem parte do array de vararg,
    // é preciso referenciar o parametro atraves do parametro nomeavel
    printAllWithPrefix("Ysmael", "Marinho", prefix = "Message: ")

    fun log(vararg entries: String) {
        //quando quero passar um vararg para outra funcao, é necessário usar o *
        //dessa maneira o kotlin entende que como vararg e não como array
        //o nome que se dá a esse recurso é: spread operator
        printAll(*entries)
    }
    log("Ysmael", "Marinho")
}