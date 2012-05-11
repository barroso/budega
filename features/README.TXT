# SETUP

 - Se nao tiver ruby, Instalar ruby
 - Eh sempre bom um: gem update --system
 - Se nao existir o comando bundle, gem install bundler
 - Entrar no diretorio features/
 - bundle install

# USO
 - Voltar para a raiz do projeto
 - Para rodar todas as features: cucumber features/
 - Para rodar apenas uma feature: cucumber features/manage_ces_cadastro_area.feature
 - Para rodar somente cenarios com tags: cucumber --tags @dev features/
