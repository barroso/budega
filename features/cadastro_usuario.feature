# language: pt

Funcionalidade: Cadastrar Usuários

  Cenário: Cadastro de Usuário
    Dado que eu esteja logado
    Dado que exista o setor "Desenvolvimento"
    
    Quando eu acesso o menu "Usuários"
    Então eu devo ver "Inserir"    
    E eu clico no botão "Inserir"
    E eu seleciono "Desenvolvimento" de "Setor"
    E eu preencho "nome" com "pedro jose"
    E eu preencho "login" com "pedro"
    E eu preencho "senha" com "12345"
    E eu clico no salvar
