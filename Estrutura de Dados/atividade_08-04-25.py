# Gerenciador de Lista de Compras do Mês 
# objetivo: Simular um sistema de gerenciamento de compras em um supermercado. 
# Enunciado: Crie um programa que: Comece com uma lista de compras vazia: 
# compras = [ ] Pergunte ao usuário se ele quer: 
# 1 ➜ Adicionar um novo item à lista  
# 2 ➜ Remover um item da lista 
# 3 ➜ Verificar se um item está ou não na lista 
# 4 ➜ mostrar o primeiro item da lista 
# 5 ➜ mostrar o último item da lista 
# 6 ➜ Mostrar os três primeiros e os três últimos itens da lista 
# 7 ➜ colocar todos os itens em letras maiúsculas 
# 8 ➜ colocar todos os itens em letras minúsculas 
# 9 ➜ Mostrar a quantidade de itens na lista 
# 10 ➜ Sair O programa deve continuar até o usuário digitar 10. 
# Requisitos: Usar in / not in para verificar se o item está na lista. Usar slices. Usar append(), remove(), len().

print ("Lista de Compras Maneira!")

def adicionar_item(compras):
    novoItem = input("Digite o nome do item que você deseja adicionar em sua lista de compras: ")
    compras.append(novoItem)
    print(compras)

def remover_item(compras):
    selecionarItem = int(input("Digite o número do item que você quer remover")) -1
    removerItem = compras[selecionarItem]
    compras.remove(remover_item)
    print(compras)

def verificar_item(compras):
    item = input("\nDigite o nome do item a ser verificado: ")
    if item in compras:
        print(f"{item} está na lista.")
    else:
        print(f"{item} não está na lista.")

def primeiro_item(compras):
    print(compras[0])

def ultimo_item(compras):
    print(compras[-1])

def tresVerificacao_item(compras):
    print(f"Os três primeiros itens da sua lista de compra são: {compras[:3]} ")
    print(f"Os três últimos itens da sua lista de compra são: {compras[:-3]} ")

def maiusculo_item(compras):
    compras = [item.upper() for item in compras]
    print(compras)

def minusculo_item(compras):
    compras = [item.lower() for item in compras]
    print(compras)

def total_item(compras):
    print(f"Total de itens: {len(compras)}")

compras = []

menu = [
     "\n1 ➜ Adicionar um novo item à lista",
        "\n2 ➜ Remover um item da lista",
        "\n3 ➜ Verificar se um item está ou não na lista",
        "\n4 ➜ Mostrar o primeiro item da lista",
        "\n5 ➜ Mostrar o último item da lista",
        "\n6 ➜ Mostrar os três primeiros e os três últimos itens da lista",
        "\n7 ➜ Colocar todos os itens em letras maiúsculas",
        "\n8 ➜ Colocar todos os itens em letras minúsculas",
        "\n9 ➜ Mostrar a quantidade de itens na lista",
        "\n10 ➜ Sair"
]
while True:
    for opcao in menu:
        print(opcao)
    escolha = int(input(" \nEscolha uma opção: "))
    if escolha == 1:
        adicionar_item(compras)
    elif escolha == 2:
        remover_item(compras)
    elif escolha ==3:
        verificar_item(compras)
    elif escolha ==4:
        primeiro_item(compras)
    elif escolha ==5:
        ultimo_item(compras)
    elif escolha ==6:
        tresVerificacao_item(compras)
    elif escolha ==7:
        maiusculo_item(compras)
    elif escolha ==8:
        minusculo_item(compras)
    elif escolha ==9:
        total_item(compras)
    elif escolha ==10:
        print("Saindo...")
        break