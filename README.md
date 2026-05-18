# Descrição da implementação do projeto
Este projeto soluciona o *Teste de Avaliação Técnica de Programador de Software – Manutenção – Full Stack* proposto pelo recrutamento da Atech.

Onde a abordagem para solucionar o problema envolveu realizar uma pesquisa por algoritmos que tivessem similaridade a descrição do problema. E a solução encontrada foi similar se não igual ao problema do maior número de intervalos sobrepostos (Maximum Overlapping Intervals) cujo algoritmo que soluciona este problema é conhecido por algoritmo de varredura linear (sweep line).

Após achar a solução que resolve o problema, a implementação do código que atende as restrições exigidas utilizando a linguagem Java como backend e Vue como frontend a partir da comunicação de APIs REST.
O código que satisfaz as restrições utiliza a biblioteca Gson para tratamento das chamadas HTTP e manipulação de objetos JSON da requisição. Algumas funções de conversão para tipos primitivos Java foram separadas em uma classe separada chamada Utils por organização pessoal.

Com objetivo de manter a simplicidade da solução, o frontend foi implementado a partir da importação da biblioteca do Vue a partir de um pacote disponivel pelo CDN(Content Delivery Network) e por este motivo que não foi necessário a build do frontend ou download das ferramentas de gerenciamento de pacotes e projetos JavaScript como é o Node.js e npm.

A explicação em alto nível exigida pode ser lida assim que a aplicação ser executada, e pode ser conferida no código fonte do arquivo `index.html`.

---

# Guia para Build e Execução da Aplicação (Linux)

Este trecho do guia descreve como construir e executar da aplicação localmente em um ambiente Linux.

---

## Pré-requisitos

- **Docker Engine** instalado e em funcionamento.
  - Instale seguindo a documentação oficial: [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)
  - Verifique a instalação abrindo um terminal e executando:
    ```bash
    docker --version
    ```
---

## Passos

### 1. Abra o terminal no diretório do projeto

Navegue até a pasta raiz do projeto (onde está o `Containerfile`):

```bash
cd /caminho/para/teste-tecnico-atec
```

### 2. (Opcional) Renomeie o `Containerfile` para `Dockerfile`

O Docker procura por `Dockerfile` por padrão. Para simplificar, renomeie:

```bash
mv Containerfile Dockerfile
```

Caso prefira manter o nome `Containerfile`, utilize a opção `-f` no comando `docker build`.

### 3. Construa a imagem Docker

Execute o comando abaixo no terminal:

```bash
docker build -t teste-tecnico-atech .
```

Se o arquivo se chamar `Containerfile`, use:

```bash
docker build -f Containerfile -t teste-tecnico-atech .
```

### 4. Execute o container

Após a construção, inicie a aplicação com:

```bash
docker run -p 9000:9000 teste-tecnico-atech
```

### 5. Acesse a aplicação

Abra o navegador e vá para:

```
http://localhost:9000
```

### 6. Parar o container

Para encerrar a execução, pressione `Ctrl + C` no terminal onde o container está rodando.

Se o container foi iniciado em segundo plano (com `-d`), liste os containers ativos e pare-o com:

```bash
docker ps
docker stop <CONTAINER_ID>
```

---

# Guia para Build e Execução da Aplicação (Windows)

Este trecho do guia descreve como construir e executar da aplicação localmente em um ambiente Windows.

---

## Pré-requisitos

- **Docker Desktop** instalado e em execução.
  - Faça o download em: [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)
  - Verifique a instalação abrindo o **PowerShell** ou **Prompt de Comando** e executando:
    ```powershell
    docker --version
    ```
---

## Passos

### 1. Abra o terminal no diretório do projeto

Navegue até a pasta raiz do projeto (onde está o `Dockerfile`):

```powershell
cd C:\caminho\para\teste-tecnico-atec
```

### 2. (Opcional) Renomeie o `Containerfile` para `Dockerfile`

O Docker procura um arquivo chamado `Dockerfile` por padrão. Para simplificar, renomeie:

```powershell
ren Containerfile Dockerfile
```

Caso prefira manter o nome `Containerfile`, use a opção `-f` no comando `docker build`.

### 3. Construa a imagem Docker

Execute o comando abaixo no terminal:

```powershell
docker build -t teste-tecnico-atec .
```

Se o arquivo se chamar `Containerfile`, use:

```powershell
docker build -f Containerfile -t teste-tecnico-atec .
```

### 4. Execute o container

Após a construção, inicie a aplicação com:

```powershell
docker run -p 9000:9000 teste-tecnico-atec
```

Isso irá mapear a porta `9000` do container para a porta `9000` do seu navegador de escolha

### 5. Acesse a aplicação

Abra o navegador e vá para:

```
http://localhost:9000
```

### 6. Parar o container

Para encerrar a execução, pressione `Ctrl + C` no terminal onde o container está rodando.

Se o container foi iniciado em modo *detached* (com `-d`), liste os containers e pare com:

```powershell
docker ps
docker stop <CONTAINER_ID>
```

---

# Build e execução manual da aplicação (Linux)

Em caso da falha na utilização do Containerfile, esta é uma um breve guia manual que tem funcionamento apenas em ambientes Linux. 
---

## Pré-requisitos

- **Java 21** instalado e em funcionamento.
  - Instale o gerenciador de versões da linguagem Java e siga as instruções para instalar a versão 21: [https://sdkman.io/](https://sdkman.io/)
  - Verifique a instalação abrindo um terminal e executando:
    ```bash
    sdk version
    ```
---

### 1. Abra o terminal no diretório do projeto

Navegue até a pasta raiz do projeto (onde está o `Containerfile`):

```bash
cd /caminho/para/teste-tecnico-atec
```

### 2. Execute a instrução de build do Gradle para o projeto
```bash
gradle run
```

### 3. Acesse a aplicação

Abra o navegador e vá para:

```
http://localhost:9000
```

### 3. Para parar a execução

Para encerrar a execução, pressione `Ctrl + C` no terminal onde o Gradle está executando.