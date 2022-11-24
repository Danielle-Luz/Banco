CREATE TABLE IF NOT EXISTS pessoa_fisica (
  	cpf VARCHAR(11) NOT NULL,
  	nome VARCHAR(100) NOT NULL,
  	data_nascimento VARCHAR(10),
	numeroConta VARCHAR(5) PRIMARY KEY,
	agencia INTEGER NOT NULL,
	telefone VARCHAR(11),
	saldo FLOAT DEFAULT 0,
	limiteCheque FLOAT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS pessoa_juridica (
  	cnpj VARCHAR(14) NOT NULL,
  	razao_social VARCHAR(100) NOT NULL,
  	nome_fantasia VARCHAR(100),
	numeroConta VARCHAR(5) PRIMARY KEY,
	agencia INTEGER NOT NULL,
	telefone VARCHAR(11),
	saldo FLOAT DEFAULT 0,
	limiteCheque FLOAT DEFAULT 0
);


