# Go - Guia Completo

![Go Logo](https://go.dev/images/go-logo-blue.svg)

## 📚 Índice

- [Sobre o Go](#sobre-o-go)
- [Contexto Histórico](#contexto-histórico)
- [Principais Características](#principais-características)
- [Configurando o Ambiente](#configurando-o-ambiente)
- [Guia de Referência Rápida](#guia-de-referência-rápida)
- [Recursos Adicionais](#recursos-adicionais)

---

## 🎯 Sobre o Go

Go (também conhecido como Golang) é uma linguagem de programação de código aberto criada pelo Google. É estaticamente tipada, compilada e conhecida por sua simplicidade, eficiência e excelente suporte a concorrência.

**Principais Usos:**
- Desenvolvimento de Servidores e APIs
- Microsserviços e Sistemas Distribuídos
- Cloud Computing e DevOps
- Ferramentas de Linha de Comando (CLI)
- Containers e Orquestração (Docker, Kubernetes)
- Network Programming

---

## 📖 Contexto Histórico

### Linha do Tempo

- **2007**: Início do desenvolvimento do Go no Google por Robert Griesemer, Rob Pike e Ken Thompson
- **Novembro 2009**: Go é anunciado publicamente como projeto open-source
- **Março 2012**: Lançamento do Go 1.0 (primeira versão estável)
- **2013**: Go 1.1 com melhorias de performance
- **2014**: Go 1.3 e 1.4 - melhorias no runtime e garbage collector
- **Agosto 2015**: Go 1.5 - compilador reescrito em Go (antes em C)
- **2016**: Go 1.6 e 1.7 - HTTP/2 e melhorias de performance
- **Fevereiro 2017**: Go 1.8 - melhorias no garbage collector
- **Agosto 2017**: Go 1.9 - type aliases e melhorias
- **2018**: Go 1.10 e 1.11 - Go Modules introduzido
- **2019**: Go 1.12 e 1.13 - Go Modules por padrão
- **2020**: Go 1.14 e 1.15 - melhorias no runtime
- **2021**: Go 1.16, 1.17 - embed e generics experimentais
- **Março 2022**: Go 1.18 - Generics oficialmente lançados
- **2023**: Go 1.20 e 1.21 - continuação de melhorias
- **2024-2025**: Go 1.22, 1.23 - range over functions e melhorias

### Por que Go foi Criado?

O Google criou Go para resolver problemas que enfrentavam com outras linguagens:
- **Compilação Lenta**: C++ tinha builds muito demorados
- **Complexidade**: C++ e Java eram muito complexos
- **Concorrência Difícil**: Programação concorrente era complicada
- **Dependências**: Gerenciamento de dependências era problemático
- **Produtividade**: Necessidade de uma linguagem simples, rápida e produtiva

Os criadores queriam combinar a eficiência de C/C++ com a simplicidade do Python e o suporte a concorrência.

---

## ⭐ Principais Características

### 1. **Simplicidade e Legibilidade**
Go tem uma sintaxe minimalista e clara.

```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, Go!")
}
```

### 2. **Compilação Rápida**
Compilador extremamente rápido, permitindo iterações ágeis.

```bash
# Compilar e executar
go run main.go

# Compilar para binário
go build -o myapp main.go
```

### 3. **Goroutines (Concorrência)**
Suporte nativo para programação concorrente com goroutines leves.

```go
package main

import (
    "fmt"
    "time"
)

func say(s string) {
    for i := 0; i < 3; i++ {
        time.Sleep(100 * time.Millisecond)
        fmt.Println(s)
    }
}

func main() {
    go say("world")  // Executa em goroutine
    say("hello")
}
```

### 4. **Channels**
Comunicação segura entre goroutines.

```go
package main

import "fmt"

func sum(numbers []int, result chan int) {
    sum := 0
    for _, num := range numbers {
        sum += num
    }
    result <- sum  // Envia resultado pelo channel
}

func main() {
    numbers := []int{1, 2, 3, 4, 5}
    result := make(chan int)
    
    go sum(numbers[:len(numbers)/2], result)
    go sum(numbers[len(numbers)/2:], result)
    
    x, y := <-result, <-result  // Recebe dos channels
    fmt.Println("Total:", x+y)
}
```

### 5. **Garbage Collection**
Gerenciamento automático de memória com GC eficiente.

### 6. **Tipagem Estática e Forte**
Segurança de tipos em tempo de compilação.

```go
var name string = "Go"
var version int = 1
// name = version  // Erro de compilação!
```

### 7. **Interfaces Implícitas**
Implementação automática de interfaces sem declaração explícita.

```go
type Writer interface {
    Write([]byte) (int, error)
}

type ConsoleWriter struct{}

func (cw ConsoleWriter) Write(data []byte) (int, error) {
    n, err := fmt.Println(string(data))
    return n, err
}

// ConsoleWriter implementa Writer automaticamente
```

### 8. **Defer, Panic e Recover**
Controle de fluxo para tratamento de erros e cleanup.

```go
func cleanup() {
    defer fmt.Println("Cleanup executado")  // Executa no final
    fmt.Println("Função executando")
}

func handlePanic() {
    defer func() {
        if r := recover(); r != nil {
            fmt.Println("Recuperado de:", r)
        }
    }()
    panic("Algo deu errado!")
}
```

### 9. **Múltiplos Valores de Retorno**
Funções podem retornar múltiplos valores facilmente.

```go
func divide(a, b float64) (float64, error) {
    if b == 0 {
        return 0, fmt.Errorf("divisão por zero")
    }
    return a / b, nil
}

result, err := divide(10, 2)
if err != nil {
    fmt.Println("Erro:", err)
} else {
    fmt.Println("Resultado:", result)
}
```

### 10. **Tooling Integrado**
Ferramentas de desenvolvimento incluídas (formato, teste, doc, etc).

```bash
go fmt ./...        # Formata código
go test ./...       # Executa testes
go doc fmt.Println  # Mostra documentação
go vet ./...        # Análise estática
```

### 11. **Cross-compilation**
Compilação fácil para diferentes plataformas.

```bash
# Windows
GOOS=windows GOARCH=amd64 go build -o app.exe

# Linux
GOOS=linux GOARCH=amd64 go build -o app

# macOS
GOOS=darwin GOARCH=amd64 go build -o app
```

### 12. **Structs e Métodos**
Programação orientada a objetos sem classes.

```go
type Person struct {
    Name string
    Age  int
}

func (p Person) Greet() string {
    return fmt.Sprintf("Hi, I'm %s", p.Name)
}

func (p *Person) Birthday() {
    p.Age++  // Método com receiver ponteiro pode modificar
}
```

---

## 🛠️ Configurando o Ambiente

### Pré-requisitos

- Sistema operacional: Linux, macOS ou Windows
- Conexão com internet para download

### Opção 1: Instalação Oficial

#### Ubuntu/Debian

```bash
# Remover versões antigas (opcional)
sudo apt remove golang-go

# Download da versão mais recente
wget https://go.dev/dl/go1.23.3.linux-amd64.tar.gz

# Extrair para /usr/local
sudo rm -rf /usr/local/go
sudo tar -C /usr/local -xzf go1.23.3.linux-amd64.tar.gz

# Adicionar ao PATH
echo 'export PATH=$PATH:/usr/local/go/bin' >> ~/.bashrc
echo 'export GOPATH=$HOME/go' >> ~/.bashrc
echo 'export PATH=$PATH:$GOPATH/bin' >> ~/.bashrc
source ~/.bashrc

# Verificar instalação
go version
```

#### macOS

**Opção 1: Homebrew (Recomendado)**
```bash
brew install go

# Verificar
go version
```

**Opção 2: Download Manual**
```bash
# Baixar do site oficial
# https://go.dev/dl/

# Extrair e mover
sudo tar -C /usr/local -xzf go1.23.3.darwin-amd64.tar.gz

# Adicionar ao PATH
echo 'export PATH=$PATH:/usr/local/go/bin' >> ~/.zshrc
source ~/.zshrc
```

#### Windows

**Opção 1: Instalador MSI**
1. Baixe o instalador: https://go.dev/dl/
2. Execute o instalador MSI
3. O instalador adiciona automaticamente ao PATH

**Opção 2: Chocolatey**
```powershell
choco install golang
```

**Opção 3: Scoop**
```powershell
scoop install go
```

### Verificar Instalação

```bash
# Versão do Go
go version

# Informações do ambiente
go env

# Verificar GOPATH
echo $GOPATH
```

### Configurar o Workspace

```bash
# Criar estrutura de diretórios
mkdir -p $HOME/go/{bin,src,pkg}

# Estrutura:
# ~/go/
#   ├── bin/    # Executáveis compilados
#   ├── pkg/    # Arquivos de pacote compilados
#   └── src/    # Código-fonte
```

### Opção 2: Usando Go Modules (Moderno)

Go Modules é o sistema de gerenciamento de dependências moderno (padrão desde Go 1.13).

#### Criar um Novo Projeto

```bash
# Criar diretório do projeto
mkdir meu-projeto-go
cd meu-projeto-go

# Inicializar módulo
go mod init github.com/seuusuario/meu-projeto-go

# Criar arquivo main
cat > main.go << 'EOF'
package main

import "fmt"

func main() {
    fmt.Println("Hello, Go Modules!")
}
EOF

# Executar
go run main.go
```

#### Estrutura de Projeto com Módulos

```
meu-projeto-go/
├── go.mod              # Definição do módulo e dependências
├── go.sum              # Checksums das dependências
├── main.go             # Arquivo principal
├── internal/           # Pacotes privados
│   └── utils/
│       └── helper.go
├── pkg/                # Pacotes públicos
│   └── models/
│       └── user.go
└── cmd/                # Aplicações principais
    └── server/
        └── main.go
```

#### Adicionar Dependências

```bash
# Adicionar dependência
go get github.com/gorilla/mux

# Atualizar dependências
go get -u ./...

# Limpar dependências não usadas
go mod tidy

# Verificar dependências
go list -m all
```

### IDEs e Editores Recomendados

#### Visual Studio Code (Recomendado)

```bash
# Instalar VS Code
# https://code.visualstudio.com/

# Instalar extensão Go
# Abra VS Code e instale: "Go" (by Go Team at Google)
```

#### GoLand (JetBrains)

```bash
# Download: https://www.jetbrains.com/go/
# IDE completa específica para Go
```

#### Vim/Neovim

```bash
# Instalar vim-go
# https://github.com/fatih/vim-go
```

### Ferramentas Essenciais

```bash
# Instalar ferramentas de desenvolvimento
go install golang.org/x/tools/gopls@latest          # Language Server
go install github.com/go-delve/delve/cmd/dlv@latest # Debugger
go install honnef.co/go/tools/cmd/staticcheck@latest # Linter
go install golang.org/x/tools/cmd/goimports@latest  # Import formatter

# Verificar instalação
gopls version
dlv version
staticcheck -version
```

---

## 📝 Guia de Referência Rápida

### Variáveis e Tipos

```go
// Declaração de variáveis
var name string = "Go"
var age int = 15
var isActive bool = true

// Declaração curta (apenas dentro de funções)
name := "Go"
age := 15

// Múltiplas variáveis
var x, y int = 1, 2
a, b := "hello", "world"

// Constantes
const Pi = 3.14
const (
    StatusOK = 200
    StatusNotFound = 404
)

// Tipos básicos
bool
string
int  int8  int16  int32  int64
uint uint8 uint16 uint32 uint64
byte // alias para uint8
rune // alias para int32 (Unicode code point)
float32 float64
complex64 complex128
```

### Arrays e Slices

```go
// Array (tamanho fixo)
var arr [5]int
arr[0] = 1

// Slice (tamanho dinâmico)
slice := []int{1, 2, 3, 4, 5}
slice = append(slice, 6)

// Make
s := make([]int, 5)      // len=5, cap=5
s := make([]int, 0, 10)  // len=0, cap=10

// Operações
len(slice)               // Tamanho
cap(slice)               // Capacidade
slice[1:3]              // Subslice
copy(dest, src)         // Copiar
```

### Maps

```go
// Criar map
ages := make(map[string]int)
ages["João"] = 30
ages["Maria"] = 25

// Map literal
scores := map[string]int{
    "João": 100,
    "Maria": 95,
}

// Verificar existência
value, exists := scores["João"]
if exists {
    fmt.Println(value)
}

// Deletar
delete(scores, "João")

// Iterar
for key, value := range scores {
    fmt.Printf("%s: %d\n", key, value)
}
```

### Estruturas de Controle

```go
// If
if x > 0 {
    fmt.Println("Positivo")
} else if x < 0 {
    fmt.Println("Negativo")
} else {
    fmt.Println("Zero")
}

// If com statement
if err := doSomething(); err != nil {
    fmt.Println("Erro:", err)
}

// For (única estrutura de loop)
for i := 0; i < 10; i++ {
    fmt.Println(i)
}

// While-style
for condition {
    // código
}

// Loop infinito
for {
    // código
    break
}

// Range
for index, value := range slice {
    fmt.Printf("%d: %d\n", index, value)
}

// Switch
switch day {
case "Monday":
    fmt.Println("Segunda")
case "Tuesday":
    fmt.Println("Terça")
default:
    fmt.Println("Outro dia")
}

// Switch sem condição (como if-else)
switch {
case x < 0:
    fmt.Println("Negativo")
case x > 0:
    fmt.Println("Positivo")
default:
    fmt.Println("Zero")
}
```

### Funções

```go
// Função básica
func add(a int, b int) int {
    return a + b
}

// Parâmetros do mesmo tipo
func multiply(a, b int) int {
    return a * b
}

// Múltiplos retornos
func divide(a, b float64) (float64, error) {
    if b == 0 {
        return 0, fmt.Errorf("divisão por zero")
    }
    return a / b, nil
}

// Retornos nomeados
func split(sum int) (x, y int) {
    x = sum * 4 / 9
    y = sum - x
    return  // naked return
}

// Função variádica
func sum(numbers ...int) int {
    total := 0
    for _, num := range numbers {
        total += num
    }
    return total
}

// Função anônima
add := func(a, b int) int {
    return a + b
}

// Closure
func counter() func() int {
    count := 0
    return func() int {
        count++
        return count
    }
}
```

### Structs

```go
// Definir struct
type Person struct {
    Name string
    Age  int
    City string
}

// Criar instância
p1 := Person{Name: "João", Age: 30, City: "SP"}
p2 := Person{"Maria", 25, "RJ"}  // Ordem importa

// Acessar campos
fmt.Println(p1.Name)
p1.Age = 31

// Struct anônima
person := struct {
    Name string
    Age  int
}{
    Name: "João",
    Age:  30,
}

// Embedding (composição)
type Employee struct {
    Person      // Embedded
    EmployeeID int
}

emp := Employee{
    Person:     Person{Name: "João", Age: 30},
    EmployeeID: 1001,
}
fmt.Println(emp.Name)  // Acesso direto ao campo do Person
```

### Métodos

```go
type Rectangle struct {
    Width  float64
    Height float64
}

// Método com valor receiver
func (r Rectangle) Area() float64 {
    return r.Width * r.Height
}

// Método com ponteiro receiver
func (r *Rectangle) Scale(factor float64) {
    r.Width *= factor
    r.Height *= factor
}

// Usar
rect := Rectangle{Width: 10, Height: 5}
fmt.Println(rect.Area())
rect.Scale(2)
```

### Interfaces

```go
// Definir interface
type Shape interface {
    Area() float64
    Perimeter() float64
}

// Implementação implícita
type Circle struct {
    Radius float64
}

func (c Circle) Area() float64 {
    return math.Pi * c.Radius * c.Radius
}

func (c Circle) Perimeter() float64 {
    return 2 * math.Pi * c.Radius
}

// Usar
var s Shape = Circle{Radius: 5}
fmt.Println(s.Area())

// Interface vazia
var any interface{} = "can hold any type"

// Type assertion
str, ok := any.(string)
if ok {
    fmt.Println(str)
}

// Type switch
switch v := any.(type) {
case string:
    fmt.Println("String:", v)
case int:
    fmt.Println("Int:", v)
default:
    fmt.Println("Unknown type")
}
```

### Tratamento de Erros

```go
// Criar erro
err := fmt.Errorf("erro: %s", "algo deu errado")

// Errors package
err := errors.New("erro simples")

// Verificar erro
result, err := someFunction()
if err != nil {
    return err  // ou log.Fatal(err)
}

// Custom error
type MyError struct {
    Code    int
    Message string
}

func (e *MyError) Error() string {
    return fmt.Sprintf("Erro %d: %s", e.Code, e.Message)
}

// Wrapping errors (Go 1.13+)
if err != nil {
    return fmt.Errorf("falha ao processar: %w", err)
}

// Unwrap
originalErr := errors.Unwrap(wrappedErr)

// Errors.Is e Errors.As
if errors.Is(err, ErrNotFound) {
    // ...
}

var myErr *MyError
if errors.As(err, &myErr) {
    // ...
}
```

### Goroutines e Channels

```go
// Iniciar goroutine
go func() {
    fmt.Println("Em goroutine")
}()

// Channel
ch := make(chan int)

// Enviar
go func() {
    ch <- 42
}()

// Receber
value := <-ch

// Buffered channel
ch := make(chan int, 2)
ch <- 1
ch <- 2
// não bloqueia até buffer encher

// Close channel
close(ch)

// Range sobre channel
for value := range ch {
    fmt.Println(value)
}

// Select
select {
case msg1 := <-ch1:
    fmt.Println("Recebeu de ch1:", msg1)
case msg2 := <-ch2:
    fmt.Println("Recebeu de ch2:", msg2)
case <-time.After(1 * time.Second):
    fmt.Println("Timeout")
default:
    fmt.Println("Nenhum canal pronto")
}
```

### Pacotes Importantes

```go
// fmt - formatação e I/O
fmt.Println("Hello")
fmt.Printf("%d\n", 42)
fmt.Sprintf("Texto: %s", "valor")

// strings
strings.Contains("hello", "ll")
strings.Split("a,b,c", ",")
strings.Join([]string{"a", "b"}, ",")
strings.ToUpper("hello")

// strconv - conversões
i, err := strconv.Atoi("42")
s := strconv.Itoa(42)

// time
now := time.Now()
time.Sleep(1 * time.Second)
duration := time.Since(start)

// os
os.Getenv("PATH")
os.Exit(1)
file, err := os.Open("file.txt")

// io/ioutil
data, err := ioutil.ReadFile("file.txt")
err := ioutil.WriteFile("file.txt", data, 0644)

// net/http
http.HandleFunc("/", handler)
http.ListenAndServe(":8080", nil)

resp, err := http.Get("https://example.com")
```

---

## 📚 Recursos Adicionais

### Documentação Oficial
- **Site Oficial**: https://go.dev/
- **Documentação**: https://go.dev/doc/
- **Tour of Go**: https://go.dev/tour/
- **Effective Go**: https://go.dev/doc/effective_go
- **Go by Example**: https://gobyexample.com/

### Tutoriais e Cursos
- **Go Tour (Oficial)**: https://go.dev/tour/
- **Go 101**: https://go101.org/
- **Learn Go with Tests**: https://quii.gitbook.io/learn-go-with-tests/
- **Gophercises**: https://gophercises.com/

### Livros Recomendados
- **"The Go Programming Language"** - Alan A. A. Donovan e Brian W. Kernighan
- **"Go in Action"** - William Kennedy, Brian Ketelsen, Erik St. Martin
- **"Concurrency in Go"** - Katherine Cox-Buday
- **"Learning Go"** - Jon Bodner
- **"Cloud Native Go"** - Matthew A. Titmus

### Comunidade
- **Go Forum**: https://forum.golangbridge.org/
- **Reddit**: r/golang
- **Gophers Slack**: https://gophers.slack.com/
- **Stack Overflow**: [go]
- **GitHub**: https://github.com/golang/go

### Frameworks e Bibliotecas Populares

**Web Frameworks:**
- **Gin**: Framework web rápido
- **Echo**: Framework web minimalista
- **Fiber**: Framework inspirado no Express.js
- **Chi**: Router leve

**Banco de Dados:**
- **GORM**: ORM popular
- **sqlx**: Extensões para database/sql
- **pgx**: Driver PostgreSQL de alta performance

**Testing:**
- **Testify**: Assertions e mocks
- **GoMock**: Framework de mocking
- **Ginkgo**: BDD testing framework

**Ferramentas:**
- **Air**: Live reload para Go
- **golangci-lint**: Meta-linter
- **Task**: Alternativa ao Make
- **Docker**: Containerização

### Projetos Famosos em Go
- **Docker**: Plataforma de containers
- **Kubernetes**: Orquestração de containers
- **Prometheus**: Sistema de monitoramento
- **Terraform**: Infrastructure as Code
- **Hugo**: Gerador de sites estáticos
- **Cobra**: Biblioteca para CLIs
- **Viper**: Gerenciamento de configurações

---

## 🎓 Exemplo: HTTP Server Completo

```go
// main.go
package main

import (
    "encoding/json"
    "fmt"
    "log"
    "net/http"
)

// User representa um usuário
type User struct {
    ID   int    `json:"id"`
    Name string `json:"name"`
    Age  int    `json:"age"`
}

// Banco de dados simulado
var users = []User{
    {ID: 1, Name: "João", Age: 30},
    {ID: 2, Name: "Maria", Age: 25},
}

// Handler para listar usuários
func getUsers(w http.ResponseWriter, r *http.Request) {
    w.Header().Set("Content-Type", "application/json")
    json.NewEncoder(w).Encode(users)
}

// Handler para homepage
func home(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Bem-vindo ao servidor Go!")
}

func main() {
    // Rotas
    http.HandleFunc("/", home)
    http.HandleFunc("/users", getUsers)
    
    // Iniciar servidor
    fmt.Println("Servidor rodando em http://localhost:8080")
    log.Fatal(http.ListenAndServe(":8080", nil))
}
```

**Executar:**
```bash
go run main.go
```

**Testar:**
```bash
curl http://localhost:8080/
curl http://localhost:8080/users
```

---

## 📄 Licença

Este documento é de uso livre para fins educacionais.

**Go** é desenvolvido pelo Google e pela comunidade open-source, sob a licença BSD-style.

---

**Última atualização**: Dezembro 2025

Feito com ❤️ para a comunidade Go

