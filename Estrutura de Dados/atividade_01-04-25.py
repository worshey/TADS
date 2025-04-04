''' Crie um programa em python que receba o nome do aluno e 4 notas.
    Deve ser feita média desse aluno e verificada as seguintes condições:
    se média menor que 5,0, o aluno está reprovado
    se média maior ou igual a 6,0, aluno aprovado
    caso contrário o aluno está de recuperação '''

aluno = input ("Digite o nome do aluno: ")
nota1 = float(input ("Digite a primeira nota: "))
nota2 = float(input ("Digite a segunda nota: "))
nota3 = float(input ("Digite a terceira nota: "))
nota4 = float(input ("Digite a quarta nota: "))
media = (nota1 + nota2 + nota3 +nota4) / 4

if media >=6:
    print (f"O aluno {aluno} foi aprovado.")
elif media >=4:
    print (f"O aluno {aluno} foi reprovado.")
else:
    print (f"O aluno {aluno} está de recuperação.")
