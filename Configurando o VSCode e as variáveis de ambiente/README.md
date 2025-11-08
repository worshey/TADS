# Configurando seu Ambiente C/C++ no Windows com VS Code

Este guia detalhado te leva passo a passo pela instalação e configuração de um ambiente de desenvolvimento C/C++ no Windows, utilizando o Visual Studio Code (VS Code) e o MinGW.

## 1. Instalando o Visual Studio Code (VS Code)

* **Passo 1:** Acesse o site oficial do VS Code: [https://code.visualstudio.com/](https://code.visualstudio.com/)

![Imagem 1](https://github.com/user-attachments/assets/2f2b1bed-8fc8-434f-a55a-3a989d95c6ce)

* **Passo 2:** Baixe o instalador para Windows (escolha a versão adequada para o seu sistema: 32 ou 64 bits).

* **Passo 3:** Execute o instalador e siga as instruções na tela para completar a instalação.
  

## 2. Instalando o MinGW

* **Passo 1:** Acesse o site do SourceForge para baixar o MinGW: [https://sourceforge.net/projects/mingw/](https://sourceforge.net/projects/mingw/)

![Imagem 2](https://github.com/user-attachments/assets/7b3a70f5-d47d-4fed-852e-176693932f37)

* **Passo 2:** Execute o instalador do MinGW.

* **Passo 3:** **Mantenha o caminho de instalação padrão (geralmente `C:\MinGW`)**. Isso simplifica a configuração das variáveis de ambiente.

* **Passo 4:** No gerenciador de instalação do MinGW, selecione os seguintes pacotes para instalação:
    * `mingw32-base-bin`
    * `mingw32-gcc-g++-bin`

 ![Imagem 3](https://github.com/user-attachments/assets/f834eaea-16be-4a9a-b80d-8f70856c2244)

* **Passo 5:** Vá em "Installation" > "Apply Changes" e clique em "Apply" para instalar os pacotes selecionados.

## 3. Instalando as Extensões C/C++ no VS Code

* **Passo 1:** Abra o VS Code.

* **Passo 2:** Clique no ícone de extensões (quadrados) na barra lateral esquerda.

![image](https://github.com/user-attachments/assets/8fb361cc-69eb-4353-ad67-f52a7018bef6)

* **Passo 3:** Na barra de pesquisa, digite "C/C++" e instale a extensão da Microsoft.

* **Passo 4:** Instale a extensão "Compile Run" para facilitar a execução de códigos diretamente no VS Code.

## 4. Configurando as Variáveis de Ambiente do Windows

* **Passo 1:** Pressione `Windows + X` e selecione "Sistema".

![Imagem 4](https://github.com/user-attachments/assets/ff9f1088-905b-400a-a857-930b5b46112c)
  
* **Passo 2:** Clique em "Proteção do sistema".

![Imagem 5](https://github.com/user-attachments/assets/adca6c09-c68e-484a-88ec-750a93427fd4)

* **Passo 3:** Na janela que se abre, clique em " Avançado" e posteriormente em "Variáveis de Ambiente...".

![Imagem 6](https://github.com/user-attachments/assets/e03035d1-e473-40b8-b377-bd6d9f2d838f)

* **Passo 4:** Na seção "Variáveis do sistema", encontre a variável "Path" e clique em "Editar".

![Imagem 7](https://github.com/user-attachments/assets/eee27f47-7f69-4c43-b754-d435dce38211)

* **Passo 5:** Clique em "Novo" e adicione o caminho para a pasta "bin" do MinGW (geralmente `C:\MinGW\bin`).

![Imagem 8](https://github.com/user-attachments/assets/2f5a2fbf-4a81-424c-86ac-0d6ba6f0ad36)

* **Passo 6:** Repita o passo 5 na seção "Variáveis do usuário".

* **Passo 7:** Clique em "OK" em todas as janelas para salvar as alterações.

## 5. Testando a Configuração

* **Passo 1:** Abra o VS Code.

* **Passo 2:** Crie um novo arquivo com a extensão `.c` ou `.cpp`.

* **Passo 3:** Escreva um código simples em C ou C++, como o clássico "Hello, World!".

* **Passo 4:** Salve o arquivo.

* **Passo 5:** Compile e execute o código.

![Compilado com sucesso!](https://github.com/user-attachments/assets/63bef070-630f-4258-8f8a-7e95d62f4636)

Se o código for executado com sucesso, seu ambiente C/C++ está pronto!


## Dicas e Observações Adicionais

* Manter o caminho padrão do MinGW ("C:\MinGW") simplifica muito a configuração das variáveis de ambiente.
* As extensões do VS Code oferecem recursos avançados para desenvolvimento em C/C++, como depuração e autocompletar.
* A variável de ambiente "Path" é essencial para que o Windows encontre os compiladores e outras ferramentas do MinGW.

Espero que este guia seja útil para você começar a programar em C/C++ no Windows!
