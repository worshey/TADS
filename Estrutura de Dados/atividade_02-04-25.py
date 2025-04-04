while True:
    nome = input("Qual é o seu nome? ")

    if nome == "SAIR" or nome == 'sair':
        print("Saindo...")
        break

    tipo = int(input("\n1 Morador \n2 Visitante \n3 Prestador de Serviço \n Qual é o seu tipo? "))
    hora = int(input("Qual é a hora? "))

    if tipo ==1:
        print(f"Olá, {nome}! Você é um morador. Acesso permitido.")
        break

    elif tipo ==2 and hora >= 22 and hora <= 8:
        print(f"Olá, {nome}! Você é um visitante.")
        break

    elif tipo ==3 and hora >= 8 and hora <= 18:
        print(f"Olá, {nome}! Você é um prestador de serviço.")
        break
    else:   
        print(f"Olá, {nome}! Você não tem acesso permitido.")
