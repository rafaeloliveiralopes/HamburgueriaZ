# HamburgueriaZ

<img width="1536" height="1024" alt="Vibrant burger banner design" src="https://github.com/user-attachments/assets/7d7d249e-7590-4b2c-a87c-34bbfcdcb99a" alt="Banner do aplicativo HamburgueriaZ"/>



---

## Sobre o projeto

Aplicativo Android desenvolvido como atividade prática da disciplina de desenvolvimento mobile, com foco em construção de interface, implementação de regras de negócio e uso de `Intents`.

O projeto simula o fluxo de pedido de uma hamburgueria, permitindo selecionar adicionais, controlar quantidade, calcular o valor total e preparar o envio do pedido por e-mail.

---

## Índice

- [Tecnologias e Ferramentas](#tecnologias-e-ferramentas)
- [Objetivos do Projeto](#objetivos-do-projeto)
- [Principais Funcionalidades](#principais-funcionalidades)
- [Telas Mobile](#telas-mobile)
- [Estrutura Implementada](#estrutura-implementada)
- [Como Executar](#como-executar)
- [Testes e Validação](#testes-e-validação)
- [Contato](#contato)

---

## Tecnologias e Ferramentas

| Stack | Versão |
|-------|--------|
| **Android Gradle Plugin** | 9.1.0 |
| **Kotlin** | 2.2.10 |
| **Android SDK** | compileSdk 36 / minSdk 23 |
| **AndroidX Core KTX** | 1.18.0 |
| **AndroidX Lifecycle Runtime** | 2.10.0 |
| **JUnit** | 4.13.2 |
| **Espresso** | 3.7.0 |
| **Android Studio** | recomendado |

---

## Objetivos do Projeto

- Construir uma interface Android com componentes visuais organizados.
- Aplicar estilização reutilizável em `themes.xml`.
- Implementar regras de negócio para montagem de pedidos.
- Calcular automaticamente o valor total com base em quantidade e adicionais.
- Exibir um resumo textual do pedido para o usuário.
- Integrar o app com clientes de e-mail usando `Intent`.

---

## Principais Funcionalidades

| Funcionalidade | Descrição |
|----------------|-----------|
| **Banner e identidade visual** | Tela inicial com faixa de título e imagem da hamburgueria. |
| **Seleção de adicionais** | Opções de bacon, queijo e onion rings com preços exibidos ao lado. |
| **Controle de quantidade** | Botões `+` e `-` com bloqueio de valores negativos. |
| **Cálculo do pedido** | Preço base do hambúrguer em `R$ 20`, com adicionais de `R$ 2`, `R$ 2` e `R$ 3`. |
| **Resumo automático** | Exibe nome do cliente, adicionais escolhidos, quantidade e preço final. |
| **Envio por e-mail** | Usa `ACTION_SENDTO` com `mailto:` e fallback compatível para abrir apps de e-mail. |
| **Validações básicas** | Impede envio sem nome do cliente ou sem quantidade selecionada. |

---
### Telas Mobile

<p align="center">
  <img src="https://github.com/user-attachments/assets/50f69c41-849d-4c06-b6dc-d97e3a6d3a97" width="250" alt="Tela 1" />
  
  <img src="https://github.com/user-attachments/assets/4515ae35-ee83-4f53-a83e-59be103c471a" width="250" alt="Tela 2" />
  
  <img src="https://github.com/user-attachments/assets/c5197365-6711-4b58-920c-8a5262a986f6" alt="Tela 3" width="250"/>
</p>

---

## Estrutura Implementada

### Interface

- Arquivo principal de layout em `app/src/main/res/layout/main_activity.xml`
- Componentes organizados em `ScrollView` para suportar telas menores
- Campo para nome do cliente
- Checkboxes para adicionais
- Seletor de quantidade
- Área de resumo do pedido
- Botão `Enviar pedido`

### Lógica

- `MainActivity.kt`
  - controla interação da interface
  - implementa `somar()`, `subtrair()` e `enviarPedido()`
  - monta o `Intent` de e-mail

- `OrderCalculator.kt`
  - centraliza cálculo de preço
  - gera o resumo textual do pedido
  - formata moeda no padrão brasileiro

---

## Como Executar

1. Abra o projeto no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Execute o app em um emulador ou dispositivo Android.
4. Preencha o nome, selecione os adicionais, ajuste a quantidade e toque em `Enviar pedido`.

Se houver um aplicativo de e-mail instalado, o app abrirá a tela de composição com assunto e corpo preenchidos.

---

## Testes e Validação

Comandos utilizados para validação do projeto:

```bash
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

Cobertura validada:

- cálculo do total com e sem adicionais
- geração do resumo do pedido
- integridade de build do aplicativo

---

## Contato

**Autor:** Rafael Oliveira Lopes  
**Projeto:** HamburgueriaZ  
**Contexto:** atividades práticas de desenvolvimento Android
