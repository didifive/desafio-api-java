package com.gft.meuamigau.enums.constants;

public class ErrorMessages {
	
	private static final String ESTA_VINCULADO_AO_ATENDIMENTO = "pois está vinculado ao(s) atendimento(s) com id(s) %s";
	/**
	 * Constants for DateTimeConverter error messages
	 */
	public static final String DATE_TO_STRING_ERROR = "Problema ao tentar converter data para string";
	public static final String STRING_TO_DATE_ERROR = "Problema ao tentar converter \'%s\' em data";

	/**
	 * Constants for UserService error messages
	 */
	public static final String USERNAME_JA_CADASTRADO = "Nome de Usuário já cadastrado";
	public static final String USUARIO_NAO_ENCONTRADO_BY_ID = "Usuário com id %d não foi encontrado";
	public static final String USUARIO_NAO_ENCONTRADO_BY_NAME = "Usuário com nome %s não foi encontrado";
	public static final String USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_PESSOA = "Usuário com id %d não pode ser excluído, "
			+ "pois está vinculado à pessoa com id %d";
	public static final String USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_ATENDIMENTO = "Usuário com id %d não pode ser excluído, "
			+ ESTA_VINCULADO_AO_ATENDIMENTO;
	public static final String USUARIO_OU_SENHA_INVALIDOS = "Informação inválida, favor checar usuário e/ou senha";
	
	/**
	 * Constants for RoleService error messages
	 */
	public static final String PERFIL_INEXISTENTE = "Não existe perfil com nome %s.";
	public static final String PERFIL_JA_CADASTRADO = "Nome de Perfil já cadastrado";
	
	/**
	 * Constants for PersonService error messages
	 */
	public static final String JA_EXISTE_CADASTRO_COM_O_E_MAIL_INFORMADO = "Já existe cadastro com o E-mail informado: %s";
	public static final String JA_EXISTE_CADASTRO_COM_O_CPF_OU_CNPJ_INFORMADO = "Já existe cadastro com o CPF ou CNPJ informado: %s";
	public static final String PESSOA_NAO_ENCONTRADA_BY_ID = "Pessoa com id %d não foi encontrada";
	public static final String PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_CLIENTE = "Pessoa com id %d não pode ser excluída, "
			+ "pois está vinculada ao cliente com id %d";
	public static final String PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_VETERINARIO = "Pessoa com id %d não pode ser excluída, "
			+ "pois está vinculada ao veterinario com id %d";
	public static final String PESSOA_JA_ESTA_VINCULADA_COM_O_USUARIO = "A pessoa com id %s já está vinculada com o usuário de id %s";
	public static final String USUARIO_JA_VINCULADO_COM_OUTRA_PESSOA = "O usuário id %s já está vinculado com outra pessoa";

	
	/**
	 * Constants for ClientService error messages
	 */
	public static final String CLIENTE_NAO_ENCONTRADO_BY_ID = "Cliente com id %d não foi encontrado";
	public static final String JA_EXISTE_CLIENTE_PARA_PESSOA_INFORMADA = "Já existe cliente cadastrado vinculado com pessoa de id %d";
	public static final String CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_CACHORRO = "Cliente com id %d não pode ser excluído, "
			+ "pois está vinculado ao(s) cachorro(s) com id(s) %s";
	public static final String CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO = "Cliente com id %d não pode ser excluído, "
			+ ESTA_VINCULADO_AO_ATENDIMENTO;
	
	/**
	 * Constants for VetService error messages
	 */
	public static final String VETERINARIO_NAO_ENCONTRADO_BY_ID = "Veterinário com id %d não foi encontrado";
	public static final String JA_EXISTE_VETERINARIO_PARA_PESSOA_INFORMADA = "Já existe veterinário cadastrado vinculado com pessoa de id %d";
	public static final String VETERINARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO = "Veterinário com id %d não pode ser excluído, "
			+ ESTA_VINCULADO_AO_ATENDIMENTO;
	
	
	/**
	 * Constants for BreedService error messages
	 */
	public static final String RACA_NAO_ENCONTRADA_BY_ID = "Raça com id %d não foi encontrada";
	public static final String ERRO_CONEXAO_THE_DOG_API = "Ocorreu um erro ao se conectar com recurso externo";
	public static final String ERRO_HTTP_STATUS_NOT_200 = "Ocorreu um erro ao tentar resgatar a informação";
	
	/**
	 * Constants for DogService error messages
	 */
	public static final String CACHORRO_NAO_ENCONTRADO_BY_ID = "Cachorro com id %d não foi encontrado";
	public static final String CACHORRO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO = "Cachorro com id %d não pode ser excluído, "
			+ ESTA_VINCULADO_AO_ATENDIMENTO;
	
	/**
	 * Constants for AttendanceService error messages
	 */
	public static final String ATENDIMENTO_NAO_ENCONTRADO_BY_ID = "Atendimento com id %d não foi encontrado";
	
	private ErrorMessages() {
		throw new IllegalAccessError("Utility Class");
	}
}
