# Configurando o ambiente de desenvolvimento Java no MacBook

Este guia detalhado te leva passo a passo pela instalação e configuração de um ambiente de desenvolvimento no mac, utilizando o terminal e o brew.

## 1. Instalando o Brew

* **Passo 1:** Abra o Terminal: Command + Espaço, digite "Terminal" e dê enter


* **Passo 2:** Instale o Homebrew -> /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

## 2. Instalando o Java
* **Passo 3:** Instale o Java -> brew install --cask temurin
*  (confirme se instalou: java -version)

## 3. Instalando o Git
* **Passo 4:** Instale o Git -> brew install git
*  (confirme se instalou: git -version)

## 4. Instalando a IDE

* **Passo 5:** Instale o IntelliJ -> brew install --cask intellij-idea-ce
*  Nota: 'ce' significa Community Edition (gratuita).


* **Passo 6:** Instale o VSCode -> brew install --cask visual-studio-code
* Nota: No VSCode, você precisará instalar o extension pack for java da microsoft quando abrir o programa pela primeira vez.