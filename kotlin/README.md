# Kotlin - Guia Completo

![Kotlin Logo](https://kotlinlang.org/docs/images/kotlin-logo.png)

## 📚 Índice

- [Sobre o Kotlin](#sobre-o-kotlin)
- [Contexto Histórico](#contexto-histórico)
- [Principais Características](#principais-características)
- [Configurando o Ambiente](#configurando-o-ambiente)
- [Guia de Referência Rápida](#guia-de-referência-rápida)
- [Recursos Adicionais](#recursos-adicionais)

---

## 🎯 Sobre o Kotlin

Kotlin é uma linguagem de programação moderna, estaticamente tipada e multiplataforma, desenvolvida pela JetBrains. É totalmente interoperável com Java e pode ser executada na JVM (Java Virtual Machine), Android, JavaScript e código nativo.

**Principais Usos:**
- Desenvolvimento Android (linguagem oficial desde 2019)
- Backend/Servidor (Spring, Ktor)
- Aplicações Desktop
- Aplicações Multiplataforma (Kotlin Multiplatform)
- Desenvolvimento Web (Kotlin/JS)

---

## 📖 Contexto Histórico

### Linha do Tempo

- **2010**: JetBrains inicia o desenvolvimento do Kotlin
- **2011**: Projeto Kotlin é revelado publicamente
- **Julho 2011**: JetBrains torna o projeto open-source sob licença Apache 2
- **Fevereiro 2016**: Lançamento do Kotlin 1.0 (primeira versão estável)
- **Maio 2017**: Google anuncia suporte oficial ao Kotlin para Android (Google I/O)
- **Março 2018**: Kotlin 1.2 com suporte multiplataforma
- **Outubro 2018**: Kotlin 1.3 com coroutines estáveis
- **Maio 2019**: Google anuncia Kotlin como linguagem preferencial para Android
- **Agosto 2020**: Kotlin 1.4 com melhorias de performance e qualidade
- **Novembro 2021**: Kotlin 1.6 com recursos de KSP e melhorias
- **2022-2023**: Kotlin 1.7, 1.8, 1.9 - evolução contínua
- **2024-2025**: Kotlin 2.x com novo compilador K2

### Por que Kotlin foi Criado?

A JetBrains desenvolveu Kotlin para resolver problemas que enfrentavam com Java:
- **Verbosidade**: Java requer muito código boilerplate
- **Null Safety**: Java não tem proteção nativa contra NullPointerException
- **Modernidade**: Java evoluía lentamente
- **Produtividade**: Necessidade de uma linguagem mais concisa e expressiva

O nome "Kotlin" vem da ilha de Kotlin, perto de São Petersburgo, Rússia, onde a JetBrains tem um escritório.

---

## ⭐ Principais Características

### 1. **Interoperabilidade com Java**
Kotlin é 100% interoperável com Java, permitindo usar bibliotecas Java existentes e migrar gradualmente projetos.

```kotlin
// Usando classes Java em Kotlin
val list = ArrayList<String>()
list.add("Kotlin")
```

### 2. **Null Safety**
Sistema de tipos que elimina NullPointerException em tempo de compilação.

```kotlin
var name: String = "Kotlin"  // Não pode ser null
var nullableName: String? = null  // Pode ser null

// Safe call
val length = nullableName?.length

// Elvis operator
val length2 = nullableName?.length ?: 0

// Non-null assertion
val length3 = nullableName!!.length  // Throws exception se null
```

### 3. **Concisão**
Menos código boilerplate que Java.

```kotlin
// Data class - gera automaticamente equals, hashCode, toString, copy
data class User(val name: String, val age: Int)

// Propriedades com getters/setters automáticos
class Person {
    var name: String = ""
        get() = field.uppercase()
        set(value) {
            field = "Name: $value"
        }
}
```

### 4. **Programação Funcional**
Suporte de primeira classe para programação funcional.

```kotlin
// Lambdas e Higher-order functions
val numbers = listOf(1, 2, 3, 4, 5)
val doubled = numbers.map { it * 2 }
val evens = numbers.filter { it % 2 == 0 }

// Function types
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

val sum = calculate(5, 3) { a, b -> a + b }
```

### 5. **Coroutines**
Suporte nativo para programação assíncrona.

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
```

### 6. **Extension Functions**
Adicionar funcionalidades a classes existentes sem herança.

```kotlin
fun String.removeSpaces(): String {
    return this.replace(" ", "")
}

val text = "Hello World".removeSpaces()  // "HelloWorld"
```

### 7. **Smart Casts**
Casting automático após verificação de tipo.

```kotlin
fun demo(x: Any) {
    if (x is String) {
        println(x.length)  // x é automaticamente convertido para String
    }
}
```

### 8. **Sealed Classes**
Classes que restringem hierarquias de tipos.

```kotlin
sealed class Result
data class Success(val data: String) : Result()
data class Error(val exception: Exception) : Result()

fun handleResult(result: Result) = when(result) {
    is Success -> println(result.data)
    is Error -> println(result.exception.message)
}
```

### 9. **Delegação**
Padrão de delegação suportado nativamente.

```kotlin
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { println(x) }
}

class Derived(b: Base) : Base by b

fun main() {
    val base = BaseImpl(10)
    Derived(base).print()  // prints 10
}
```

### 10. **Multiplatform**
Compartilhar código entre JVM, JavaScript, Native e mais.

---

## 🛠️ Configurando o Ambiente

### Pré-requisitos

- **Java JDK** (versão 8 ou superior)
- **IDE** (IntelliJ IDEA recomendado)

### Opção 1: IntelliJ IDEA (Recomendado)

#### 1. Instalar o JDK

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

**macOS (com Homebrew):**
```bash
brew install openjdk@17
```

**Windows:**
- Baixe o JDK do site oficial: https://www.oracle.com/java/technologies/downloads/
- Ou use o OpenJDK: https://adoptium.net/

#### 2. Verificar instalação do Java

```bash
java -version
javac -version
```

#### 3. Instalar IntelliJ IDEA

**Ubuntu/Debian:**
```bash
# Via Snap
sudo snap install intellij-idea-community --classic

# Ou baixe diretamente
wget https://download.jetbrains.com/idea/ideaIC-2024.2.3.tar.gz
tar -xzf ideaIC-2024.2.3.tar.gz
cd idea-IC-*/bin
./idea.sh
```

**macOS:**
```bash
brew install --cask intellij-idea-ce
```

**Windows:**
- Baixe o instalador: https://www.jetbrains.com/idea/download/

#### 4. Criar um Projeto Kotlin

1. Abra o IntelliJ IDEA
2. Selecione **New Project**
3. Escolha **Kotlin** no menu lateral
4. Selecione o template desejado (Console Application, por exemplo)
5. Configure:
   - **Name**: Seu projeto
   - **Location**: Onde salvar
   - **Build System**: Gradle (Kotlin DSL) ou Maven
   - **JDK**: Selecione o JDK instalado
6. Clique em **Create**

### Opção 2: Linha de Comando (Gradle)

#### 1. Instalar o SDKMAN! (Gerenciador de SDKs)

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

#### 2. Instalar Kotlin e Gradle

```bash
sdk install kotlin
sdk install gradle
```

#### 3. Criar um projeto Gradle com Kotlin

```bash
mkdir meu-projeto-kotlin
cd meu-projeto-kotlin
gradle init
```

Selecione:
- Type of project: **2: application**
- Implementation language: **4: Kotlin**
- Build script DSL: **1: Kotlin**
- Test framework: **4: JUnit Jupiter**

#### 4. Estrutura do Projeto

```
meu-projeto-kotlin/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle/
├── gradlew
├── gradlew.bat
└── src/
    ├── main/
    │   └── kotlin/
    │       └── App.kt
    └── test/
        └── kotlin/
            └── AppTest.kt
```

#### 5. Compilar e Executar

```bash
# Compilar
./gradlew build

# Executar
./gradlew run
```

### Opção 3: Compilador Standalone

#### 1. Baixar o Compilador Kotlin

```bash
wget https://github.com/JetBrains/kotlin/releases/download/v1.9.21/kotlin-compiler-1.9.21.zip
unzip kotlin-compiler-1.9.21.zip
```

#### 2. Adicionar ao PATH

```bash
export PATH="$PATH:/caminho/para/kotlinc/bin"
```

#### 3. Criar um arquivo Kotlin

```kotlin
// hello.kt
fun main() {
    println("Hello, Kotlin!")
}
```

#### 4. Compilar e Executar

```bash
# Compilar
kotlinc hello.kt -include-runtime -d hello.jar

# Executar
java -jar hello.jar
```

### Opção 4: Kotlin para Android

#### 1. Instalar Android Studio

Baixe em: https://developer.android.com/studio

#### 2. Criar Novo Projeto

1. Abra Android Studio
2. **New Project**
3. Selecione **Empty Activity**
4. Configure:
   - **Name**: Seu app
   - **Language**: Kotlin
   - **Minimum SDK**: API 21 ou superior
5. Clique em **Finish**

### Opção 5: Kotlin Playground Online

Para testes rápidos sem instalação:
- https://play.kotlinlang.org/

---

## 📝 Guia de Referência Rápida

### Variáveis e Tipos

```kotlin
// Variáveis
val imutavel = 10        // val - imutável (como final em Java)
var mutavel = 20         // var - mutável

// Tipos básicos
val inteiro: Int = 42
val longo: Long = 42L
val double: Double = 3.14
val float: Float = 3.14f
val boolean: Boolean = true
val char: Char = 'A'
val string: String = "Hello"

// Inferência de tipo
val numero = 42          // Int é inferido
```

### Strings

```kotlin
val name = "Kotlin"
val greeting = "Hello, $name!"               // Template
val info = "Length: ${name.length}"          // Expressão

// Raw string
val text = """
    Linha 1
    Linha 2
    Linha 3
""".trimIndent()
```

### Funções

```kotlin
// Função básica
fun sum(a: Int, b: Int): Int {
    return a + b
}

// Função de expressão
fun multiply(a: Int, b: Int) = a * b

// Parâmetros padrão
fun greet(name: String = "World") = "Hello, $name!"

// Parâmetros nomeados
fun createUser(name: String, age: Int, email: String) { }
createUser(name = "John", age = 30, email = "john@example.com")

// Função vararg
fun printAll(vararg messages: String) {
    messages.forEach { println(it) }
}
```

### Controle de Fluxo

```kotlin
// If como expressão
val max = if (a > b) a else b

// When (substituto do switch)
when (x) {
    1 -> println("Um")
    2, 3 -> println("Dois ou Três")
    in 4..10 -> println("Entre 4 e 10")
    is String -> println("É uma String")
    else -> println("Outro")
}

// Loops
for (i in 1..10) { }                // 1 até 10
for (i in 1 until 10) { }           // 1 até 9
for (i in 10 downTo 1) { }          // 10 até 1
for (i in 1..10 step 2) { }         // 1, 3, 5, 7, 9

list.forEach { item -> println(item) }
list.forEachIndexed { index, item -> println("$index: $item") }
```

### Classes

```kotlin
// Classe básica
class Person(val name: String, var age: Int)

// Com corpo
class Person(val name: String, var age: Int) {
    init {
        println("Pessoa criada: $name")
    }
    
    fun greet() = "Hello, I'm $name"
}

// Data class
data class User(val id: Int, val name: String)

// Herança
open class Animal(val name: String)
class Dog(name: String, val breed: String) : Animal(name)

// Interface
interface Clickable {
    fun click()
    fun showOff() = println("Default implementation")
}
```

### Collections

```kotlin
// List
val list = listOf(1, 2, 3)                    // Imutável
val mutableList = mutableListOf(1, 2, 3)      // Mutável

// Set
val set = setOf(1, 2, 3)
val mutableSet = mutableSetOf(1, 2, 3)

// Map
val map = mapOf("a" to 1, "b" to 2)
val mutableMap = mutableMapOf("a" to 1)

// Operações
list.filter { it > 1 }
list.map { it * 2 }
list.find { it > 2 }
list.groupBy { it % 2 }
list.sortedBy { it }
```

### Lambdas

```kotlin
val sum: (Int, Int) -> Int = { a, b -> a + b }

// Com receiver
val isEven: Int.() -> Boolean = { this % 2 == 0 }
println(4.isEven())  // true

// Trailing lambda
list.filter { it > 0 }
```

### Scope Functions

```kotlin
// let - chama um bloco com o objeto como parâmetro
val length = text?.let { it.length }

// run - chama um bloco com o objeto como receiver
val result = person.run {
    name = "John"
    age = 30
    this
}

// with - não é extension function
val result = with(person) {
    name = "John"
    age = 30
}

// apply - configura objeto e retorna ele
val person = Person().apply {
    name = "John"
    age = 30
}

// also - chama ação e retorna objeto
val numbers = mutableListOf(1, 2, 3).also {
    println("List created")
}
```

### Exceptions

```kotlin
try {
    // código
} catch (e: Exception) {
    // tratamento
} finally {
    // cleanup
}

// Como expressão
val result = try {
    parseInt(input)
} catch (e: NumberFormatException) {
    null
}
```

---

## 📚 Recursos Adicionais

### Documentação Oficial
- **Site Oficial**: https://kotlinlang.org/
- **Documentação**: https://kotlinlang.org/docs/home.html
- **Reference**: https://kotlinlang.org/docs/reference/
- **Kotlin Koans**: https://play.kotlinlang.org/koans/overview

### Tutoriais e Cursos
- **Kotlin Bootcamp (Google)**: https://developer.android.com/courses/kotlin-bootcamp/overview
- **Kotlin for Java Developers (Coursera)**: https://www.coursera.org/learn/kotlin-for-java-developers
- **JetBrains Academy**: https://www.jetbrains.com/academy/

### Livros Recomendados
- **"Kotlin in Action"** - Dmitry Jemerov e Svetlana Isakova
- **"Head First Kotlin"** - Dawn Griffiths e David Griffiths
- **"Programming Kotlin"** - Venkat Subramaniam
- **"Kotlin Programming: The Big Nerd Ranch Guide"**

### Comunidade
- **Kotlin Slack**: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
- **Reddit**: r/Kotlin
- **Stack Overflow**: [kotlin]
- **GitHub**: https://github.com/JetBrains/kotlin

### Frameworks Populares
- **Ktor**: Framework web assíncrono
- **Spring Boot**: Suporte completo para Kotlin
- **Exposed**: Framework SQL
- **Koin**: Injeção de dependências
- **Compose Multiplatform**: UI multiplataforma

### Ferramentas
- **Detekt**: Análise estática de código
- **ktlint**: Linter e formatador
- **Dokka**: Gerador de documentação
- **Gradle Kotlin DSL**: Build scripts em Kotlin

---

## 🎓 Exemplo: Hello World Completo

```kotlin
// Main.kt
package com.exemplo

/**
 * Função principal do programa
 */
fun main(args: Array<String>) {
    // Imprime mensagem
    println("Hello, Kotlin!")
    
    // Verifica argumentos
    if (args.isNotEmpty()) {
        println("Argumentos: ${args.joinToString()}")
    }
    
    // Cria e usa objeto
    val person = Person("João", 25)
    person.introduce()
}

/**
 * Classe que representa uma pessoa
 */
data class Person(val name: String, val age: Int) {
    fun introduce() {
        println("Olá! Meu nome é $name e tenho $age anos.")
    }
}
```

---

## 📄 Licença

Este documento é de uso livre para fins educacionais.

**Kotlin** é desenvolvido pela JetBrains e está sob a licença Apache 2.0.

---

**Última atualização**: Dezembro 2025

Feito com ❤️ para a comunidade Kotlin
