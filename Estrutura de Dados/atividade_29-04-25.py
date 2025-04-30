evento_tecnico = set()
evento_cultural = set()

while True:
    print("=== Menu de Consulta de Participação ===")
    print("=== Menu de Consulta de Participação ===")
    print("1 - Inserir participantes do Evento Técnico")
    print("2 - Inserir participantes do Evento Cultural")
    print("3 - Remover participante de um evento")
    print("4 - Mostrar funcionários que participaram dos DOIS eventos")
    print("5 - Mostrar funcionários que participaram de APENAS UM evento")
    print("6 - Consultar se um funcionário participou (e de quais eventos)")
    print("7 - Sair")
    opcao = input("Escolha uma opção: ")

    if opcao == "1":
        qtd = int(input("Quantos participantes serão inseridos? "))
        for _ in range(qtd):
            nome = input("Nome: ")
            evento_tecnico.add(nome)

    elif opcao == "2":
        qtd = int(input("Quantos participantes serão inseridos? "))
        for _ in range(qtd):
            nome = input("Nome: ")
            evento_cultural.add(nome)

    elif opcao == "3":
        tipo = input("De qual evento deseja remover o participante? (Técnico/Cultural): ")
        nome = input("Nome do participante: ")
        if tipo.lower() in ("técnico", "tecnico"):
            evento_tecnico.discard(nome)
        elif tipo.lower() == "cultural":
            evento_cultural.discard(nome)
        else:
            print("Evento inválido.")

    elif opcao == "4":
        participantes = evento_tecnico & evento_cultural
        print("Participantes dos dois eventos:", participantes)

    elif opcao == "5":
        participantes = evento_tecnico ^ evento_cultural
        print("Participantes de apenas um evento:", participantes)

    elif opcao == "6":
        nome = input("Nome do funcionário: ")
        tecnico = nome in evento_tecnico
        cultural = nome in evento_cultural
        if tecnico and cultural:
            print(f"{nome} participou dos dois eventos.")
        elif tecnico:
            print(f"{nome} participou apenas do Evento Técnico.")
        elif cultural:
            print(f"{nome} participou apenas do Evento Cultural.")
        else:
            print(f"{nome} não participou de nenhum evento.")

    elif opcao == "7":
        break

    else:
        print("Opção inválida.")