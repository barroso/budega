require 'rubygems'
require 'pg'
require 'term/ansicolor'
include Term::ANSIColor

$db_name = "budega"
$data = File.read("./db/create_data.sql")

def reload_db
  puts "Limpando Banco de Dados..."
  begin
	conn = PGconn.connect( :dbname => $db_name, :user => 'postgres')
	#Essa função pode não existir: alter_trigger
    conn.exec("select alter_trigger(table_name, 'DISABLE') FROM information_schema.constraint_column_usage  where table_schema='public'  and table_catalog='#{$db_name}' group by table_name;")

    tables = conn.exec("SELECT table_name FROM information_schema.tables WHERE table_type = 'BASE TABLE' AND table_schema NOT IN ('pg_catalog', 'information_schema');")
    delete_tables = tables.map {|table| "delete from #{table['table_name']};"}.join()

    conn.exec delete_tables
    conn.exec("select alter_trigger(table_name, 'ENABLE') FROM information_schema.constraint_column_usage  where table_schema='public'  and table_catalog='#{$db_name}' group by table_name;")
	puts "#{green("Banco de Dados limpo com sucesso...")}"
    
    puts "Populando Banco de Dados..."
    conn.exec($data)
	puts "#{green("Banco de Dados populado com sucesso...")}"
  ensure
    conn.finish if conn
  end
end

if ARGV[0] == '--start'
  reload_db
end