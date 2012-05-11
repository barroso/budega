# language: pt

Funcionalidade: Cadastrar Setores

  Cenário: Cadastro de setor
    Dado que eu esteja logado
    
    Quando eu acesso o menu "Setores"
    Então eu devo ver "Inserir"    
    E eu clico no botão "Inserir"
    E eu preencho "nome" com "financeiro"
    E eu clico no salvar
    Então eu devo ver "financeiro"