package com.gft.meuamigau.enums.constants.api.v1;

public final class ControllersAnnotationsV1 {
	
	/**
	 * Constants for AuthenticationControllerDocs
	 */
	public static final String AUTHENTICATION_CONTROLER_TAG = "1. Autenticação";
	//authenticate method
	public static final String AUTHENTICATION_CONTROLER_AUTHENTICATE_OPERATION_SUMMARY = "Generate Token";
	public static final String AUTHENTICATION_CONTROLER_AUTHENTICATE_OPERATION_DESCRIPTION = "Token creation operation";
	public static final String AUTHENTICATION_CONTROLER_AUTHENTICATE_200_DESCRIPTION = "Success token creation";
	public static final String AUTHENTICATION_CONTROLER_AUTHENTICATE_401_DESCRIPTION = "User (email) or password invalid";
	
	/**
	 * Constants for UserControllerDocs
	 */
	public static final String USER_CONTROLER_TAG = "2. Usuários";
	//create method
	public static final String USER_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar Usuário";
	public static final String USER_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Realizar o cadastro de usuário";
	public static final String USER_CONTROLLER_CREATE_201_DESCRIPTION = "Usuário criado com sucesso";
	public static final String USER_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
	public static final String USER_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String USER_CONTROLLER_CREATE_409_DESCRIPTION = "Violação de integridade de dados, verificar mensagem de retorno";
	//findAll method
	public static final String USER_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Usuários";
	public static final String USER_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os usuários cadastrados";
	public static final String USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = "Quantidade de objetos por página";
	public static final String USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = "10";
	public static final String USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE = "O número da página que quer retornar";
	public static final String USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = "0";
	public static final String USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = "Nome do campo que deseja ordenar. "
			+ "Seguido da direção de ordenação desejada \',asc|desc\' (ascendente/descendente). "
			+ "A direção de ordenação padrão é ascendent. "
			+ "Aceita ordenação múltipla. ";
	public static final String USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = "id,desc";
	public static final String USER_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de usuários cadastradas";
	//findById method
	public static final String USER_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Usuário";
	public static final String USER_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar usuário conforme id informado";
	public static final String USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id da usuário";
	public static final String USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = "1";
	public static final String USER_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do usuário buscado";
	public static final String USER_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = USER_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String USER_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Usuário com id informado não foi encontrada";
	//update method
	public static final String USER_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Usuário";
	public static final String USER_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização de usuário";
	public static final String USER_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String USER_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String USER_CONTROLLER_UPDATE_200_DESCRIPTION = "Usuário atualizado com sucesso";
	public static final String USER_CONTROLLER_UPDATE_400_DESCRIPTION = USER_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String USER_CONTROLLER_UPDATE_403_DESCRIPTION = USER_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String USER_CONTROLLER_UPDATE_404_DESCRIPTION = USER_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String USER_CONTROLLER_UPDATE_409_DESCRIPTION = USER_CONTROLLER_CREATE_409_DESCRIPTION;
	//delete method
	public static final String USER_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Usuário";
	public static final String USER_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão da usuário";
	public static final String USER_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String USER_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String USER_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Usuário excluído com sucesso";
	public static final String USER_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION = USER_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String USER_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION = USER_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String USER_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION = USER_CONTROLLER_CREATE_409_DESCRIPTION;
	//handlePassword method
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_OPERATION_SUMMARY = "Trocar Senha";
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_OPERATION_DESCRIPTION = "Realizar troca de senha pelo próprio usuário";
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_PARAMETER_USERNAME_DESCRIPTION = "Nome de usuário";
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_PARAMETER_USERNAME_EXAMPLE = "username@gft.com";
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_204_DESCRIPTION = "Senha de usuário alterada com sucesso";
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_400_DESCRIPTION = USER_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_403_DESCRIPTION = USER_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_404_DESCRIPTION = USER_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String USER_CONTROLLER_HANDLE_PASSWORD_409_DESCRIPTION = USER_CONTROLLER_CREATE_409_DESCRIPTION;
	
	/**
	 * Constants for PersonControllerDocs
	 */
	public static final String PERSON_CONTROLER_TAG = "3. Pessoas";
	//create method
	public static final String PERSON_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar Pessoa";
	public static final String PERSON_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Realizar o cadastro de pessoa";
	public static final String PERSON_CONTROLLER_CREATE_201_DESCRIPTION = "Pessoa criada com sucesso";
	public static final String PERSON_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
	public static final String PERSON_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String PERSON_CONTROLLER_CREATE_409_DESCRIPTION = "Violação de integridade de dados, verificar mensagem de retorno";
	//findAll method
	public static final String PERSON_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Pessoas";
	public static final String PERSON_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todas as pessoas cadastradas";
	public static final String PERSON_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
	public static final String PERSON_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
	public static final String PERSON_CONTROLLER_FIND_ALL_PARAMETER_PAGE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
	public static final String PERSON_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
	public static final String PERSON_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
	public static final String PERSON_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
	public static final String PERSON_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de pessoas cadastradas";
	//findById method
	public static final String PERSON_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Pessoa";
	public static final String PERSON_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar pessoa conforme id informado";
	public static final String PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id da pessoa";
	public static final String PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String PERSON_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe da pessoa buscada";
	public static final String PERSON_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = PERSON_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String PERSON_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Pessoa com id informado não foi encontrada";
	//update method
	public static final String PERSON_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Pessoa";
	public static final String PERSON_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização de pessoa";
	public static final String PERSON_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String PERSON_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String PERSON_CONTROLLER_UPDATE_200_DESCRIPTION = "Pessoa atualizada com sucesso";
	public static final String PERSON_CONTROLLER_UPDATE_400_DESCRIPTION = PERSON_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String PERSON_CONTROLLER_UPDATE_403_DESCRIPTION = PERSON_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String PERSON_CONTROLLER_UPDATE_404_DESCRIPTION = PERSON_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String PERSON_CONTROLLER_UPDATE_409_DESCRIPTION = PERSON_CONTROLLER_CREATE_409_DESCRIPTION;
	//delete method
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Pessoa";
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão da pessoa";
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Pessoa excluída com sucesso";
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION = PERSON_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION = PERSON_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String PERSON_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION = PERSON_CONTROLLER_CREATE_409_DESCRIPTION;
	//handleUser method
	public static final String PERSON_CONTROLLER_HANDLE_USER_OPERATION_SUMMARY = "Trocar usuário da pessoa";
	public static final String PERSON_CONTROLLER_HANDLE_USER_OPERATION_DESCRIPTION = "Realizar troca de usuário vinculado à pessoa";
	public static final String PERSON_CONTROLLER_HANDLE_USER_PARAMETER_ID_DESCRIPTION = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String PERSON_CONTROLLER_HANDLE_USER_PARAMETER_ID_EXAMPLE = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String PERSON_CONTROLLER_HANDLE_USER_PARAMETER_NEW_USER_ID_DESCRIPTION = "Id/Código do novo usuário a ser vinculado na pessoa";
	public static final String PERSON_CONTROLLER_HANDLE_USER_PARAMETER_NEW_USER_ID_EXAMPLE = PERSON_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String PERSON_CONTROLLER_HANDLE_USER_PARAMETER_NEW_USER_USERNAME_DESCRIPTION = "Nome de usuário do novo usuário a ser vinculado na pessoa";
	public static final String PERSON_CONTROLLER_HANDLE_USER_PARAMETER_NEW_USER_USERNAME_EXAMPLE = "novousuario@gft.com";
	public static final String PERSON_CONTROLLER_HANDLE_USER_204_DESCRIPTION = "Novo usuário vinculado na pessoa com sucesso";
	public static final String PERSON_CONTROLLER_HANDLE_USER_403_DESCRIPTION = PERSON_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String PERSON_CONTROLLER_HANDLE_USER_404_DESCRIPTION = PERSON_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String PERSON_CONTROLLER_HANDLE_USER_409_DESCRIPTION = PERSON_CONTROLLER_CREATE_409_DESCRIPTION;
	
	/**
	 * Constants for ClientControllerDocs
	 */
	public static final String CLIENT_CONTROLER_TAG = "4. Clientes";
	//create method
	public static final String CLIENT_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar Cliente";
	public static final String CLIENT_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Realizar o cadastro de cliente";
	public static final String CLIENT_CONTROLLER_CREATE_201_DESCRIPTION = "Cliente criado com sucesso";
	public static final String CLIENT_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
	public static final String CLIENT_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String CLIENT_CONTROLLER_CREATE_409_DESCRIPTION = "Violação de integridade de dados, verificar mensagem de retorno";
	//findAll method
	public static final String CLIENT_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Clientes";
	public static final String CLIENT_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os clientes cadastrados";
	public static final String CLIENT_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
	public static final String CLIENT_CONTROLLER_FIND_ALL_PARAMETER_PAGE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
	public static final String CLIENT_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
	public static final String CLIENT_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
	public static final String CLIENT_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de clientes cadastrados";
	//findById method
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Cliente";
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar cliente conforme id informado";
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do cliente";
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do cliente buscado";
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = CLIENT_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Cliente com id informado não foi encontrado";
	//update
	public static final String CLIENT_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Cliente";
	public static final String CLIENT_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização de cliente";
	public static final String CLIENT_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String CLIENT_CONTROLLER_UPDATE_200_DESCRIPTION = "Cliente atualizada com sucesso";
	public static final String CLIENT_CONTROLLER_UPDATE_400_DESCRIPTION = CLIENT_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_UPDATE_403_DESCRIPTION = CLIENT_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_UPDATE_404_DESCRIPTION = CLIENT_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_UPDATE_409_DESCRIPTION = CLIENT_CONTROLLER_CREATE_409_DESCRIPTION;
	//delete method
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Cliente";
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão de cadastro de cliente";
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Cliente excluído com sucesso";
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION = CLIENT_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION = CLIENT_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String CLIENT_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION = CLIENT_CONTROLLER_CREATE_409_DESCRIPTION;
	
	/**
	 * Constants for VetControllerDocs
	 */
	public static final String VET_CONTROLER_TAG = "5. Veterinários";
	//create method
	public static final String VET_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar Veterinário";
	public static final String VET_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Realizar o cadastro de veterinário";
	public static final String VET_CONTROLLER_CREATE_201_DESCRIPTION = "Veterinário criado com sucesso";
	public static final String VET_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
	public static final String VET_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String VET_CONTROLLER_CREATE_409_DESCRIPTION = "Violação de integridade de dados, verificar mensagem de retorno";
	//findAll method
	public static final String VET_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Veterinários";
	public static final String VET_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os veterinários cadastrados";
	public static final String VET_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
	public static final String VET_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
	public static final String VET_CONTROLLER_FIND_ALL_PARAMETER_PAGE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
	public static final String VET_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
	public static final String VET_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
	public static final String VET_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
	public static final String VET_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de veterinários cadastrados";
	//findById method
	public static final String VET_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Veterinário";
	public static final String VET_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar veterinário conforme id informado";
	public static final String VET_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do veterinário";
	public static final String VET_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String VET_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do veterinário buscado";
	public static final String VET_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = VET_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String VET_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Veterinário com id informado não foi encontrado";
	//update
	public static final String VET_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Veterinário";
	public static final String VET_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização de veterinário";
	public static final String VET_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = VET_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String VET_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = VET_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String VET_CONTROLLER_UPDATE_200_DESCRIPTION = "Veterinário atualizada com sucesso";
	public static final String VET_CONTROLLER_UPDATE_400_DESCRIPTION = VET_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String VET_CONTROLLER_UPDATE_403_DESCRIPTION = VET_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String VET_CONTROLLER_UPDATE_404_DESCRIPTION = VET_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String VET_CONTROLLER_UPDATE_409_DESCRIPTION = VET_CONTROLLER_CREATE_409_DESCRIPTION;
	//delete method
	public static final String VET_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Veterinário";
	public static final String VET_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão de cadastro de veterinário";
	public static final String VET_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = VET_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String VET_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = VET_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String VET_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Veterinário excluído com sucesso";
	public static final String VET_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION = VET_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String VET_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION = VET_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String VET_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION = VET_CONTROLLER_CREATE_409_DESCRIPTION;
	
	/**
	 * Constants for BreedControllerDocs
	 */
	public static final String BREED_CONTROLER_TAG = "6. Raças de Cachorros";
	//findAll method
	public static final String BREED_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Raças";
	public static final String BREED_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todas as raças disponíveis";
	public static final String BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
	public static final String BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
	public static final String BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
	public static final String BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
	public static final String BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
	public static final String BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
	public static final String BREED_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de raças disponíveis";
	//findById method
	public static final String BREED_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Raça";
	public static final String BREED_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar raça conforme id informado";
	public static final String BREED_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id da raça";
	public static final String BREED_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String BREED_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe da raça buscada";
	public static final String BREED_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String BREED_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Raça com id informado não foi encontrada";
	//findAllByName method
	public static final String BREED_CONTROLLER_FIND_ALL_BY_NAME_OPERATION_SUMMARY = "Listar Raças Por Nome";
	public static final String BREED_CONTROLLER_FIND_ALL_BY_NAME_OPERATION_DESCRIPTION = "Listar raças filtradas por Nome";
	public static final String BREED_CONTROLLER_FIND_ALL_BY_NAME_200_DESCRIPTION = "Retorna lista paginada de raças localizadas pelo nome buscado";
	//findImageById method
	public static final String BREED_CONTROLLER_IMAGE_BY_ID_OPERATION_SUMMARY = "Imagem exemplar da raça";
	public static final String BREED_CONTROLLER_FIND_IMAGE_BY_ID_OPERATION_DESCRIPTION = "Retorna dados da raça conforme id informado";
	public static final String BREED_CONTROLLER_FIND_IMAGE_BY_ID_PARAMETER_ID_DESCRIPTION = "Id da imagem";
	public static final String BREED_CONTROLLER_FIND_IMAGE_BY_ID_PARAMETER_ID_EXAMPLE = "H6h17XOYn";
	public static final String BREED_CONTROLLER_FIND_IMAGE_BY_ID_200_DESCRIPTION = "Retorna detalhe da imagem buscada";
	public static final String BREED_CONTROLLER_FIND_IMAGE_BY_ID_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String BREED_CONTROLLER_FIND_IMAGE_BY_ID_404_DESCRIPTION = "Imagem com id informado não foi localizada";
	
	/**
	 * Constants for DogControllerDocs
	 */
	public static final String DOG_CONTROLER_TAG = "7. Cachorros";
	//create method
	public static final String DOG_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar Cachorro";
	public static final String DOG_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Realizar o cadastro de cachorro";
	public static final String DOG_CONTROLLER_CREATE_201_DESCRIPTION = "Cachorro criado com sucesso";
	public static final String DOG_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
	public static final String DOG_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String DOG_CONTROLLER_CREATE_409_DESCRIPTION = "Violação de integridade de dados, verificar mensagem de retorno";
	//findAll method
	public static final String DOG_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Cachorros";
	public static final String DOG_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os cachorros cadastrados";
	public static final String DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
	public static final String DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
	public static final String DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
	public static final String DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
	public static final String DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
	public static final String DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
	public static final String DOG_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de cachorros cadastrados";
	//findById method
	public static final String DOG_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Cachorro";
	public static final String DOG_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar cachorro conforme id informado";
	public static final String DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do cachorro";
	public static final String DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String DOG_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do cachorro buscado";
	public static final String DOG_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = DOG_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String DOG_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Cachorro com id informado não foi encontrado";
	//update
	public static final String DOG_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Cachorro";
	public static final String DOG_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização de cachorro";
	public static final String DOG_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String DOG_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String DOG_CONTROLLER_UPDATE_200_DESCRIPTION = "Cachorro atualizada com sucesso";
	public static final String DOG_CONTROLLER_UPDATE_400_DESCRIPTION = DOG_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String DOG_CONTROLLER_UPDATE_403_DESCRIPTION = DOG_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String DOG_CONTROLLER_UPDATE_404_DESCRIPTION = DOG_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String DOG_CONTROLLER_UPDATE_409_DESCRIPTION = DOG_CONTROLLER_CREATE_409_DESCRIPTION;
	//delete method
	public static final String DOG_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Cachorro";
	public static final String DOG_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão de cadastro de cachorro";
	public static final String DOG_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String DOG_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String DOG_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Cachorro excluído com sucesso";
	public static final String DOG_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION = DOG_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String DOG_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION = DOG_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String DOG_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION = DOG_CONTROLLER_CREATE_409_DESCRIPTION;
	//findAllByClient method
	public static final String DOG_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_SUMMARY = "Listar Cachorros";
	public static final String DOG_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_DESCRIPTION = "Lista de cachorros filtrados por cliente";
	public static final String DOG_CONTROLLER_FIND_ALL_BY_CLIENT_200_DESCRIPTION = "Retorna lista paginada de cachorros filtrados por cliente";
	
	/**
	 * Constants for AttendanceControllerDocs
	 */
	public static final String ATTENDANCE_CONTROLER_TAG = "8. Atendimentos";
	//create method
	public static final String ATTENDANCE_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar Atendimento";
	public static final String ATTENDANCE_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Realizar o cadastro de atendimento";
	public static final String ATTENDANCE_CONTROLLER_CREATE_201_DESCRIPTION = "Atendimento criado com sucesso";
	public static final String ATTENDANCE_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
	public static final String ATTENDANCE_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
	public static final String ATTENDANCE_CONTROLLER_CREATE_409_DESCRIPTION = "Violação de integridade de dados, verificar mensagem de retorno";
	//findAll method
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Atendimentos";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os atendimentos cadastrados";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = USER_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = USER_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de atendimentos cadastrados";
	//findById method
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Atendimento";
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar atendimento conforme id informado";
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do atendimento";
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = USER_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do atendimento buscado";
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION = ATTENDANCE_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_FIND_BY_ID_404_DESCRIPTION = "Atendimento com id informado não foi encontrado";
	//update
	public static final String ATTENDANCE_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Atendimento";
	public static final String ATTENDANCE_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização de atendimento";
	public static final String ATTENDANCE_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String ATTENDANCE_CONTROLLER_UPDATE_200_DESCRIPTION = "Atendimento atualizada com sucesso";
	public static final String ATTENDANCE_CONTROLLER_UPDATE_400_DESCRIPTION = ATTENDANCE_CONTROLLER_CREATE_400_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_UPDATE_403_DESCRIPTION = ATTENDANCE_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_UPDATE_404_DESCRIPTION = ATTENDANCE_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_UPDATE_409_DESCRIPTION = ATTENDANCE_CONTROLLER_CREATE_409_DESCRIPTION;
	//delete method
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Atendimento";
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão de cadastro de atendimento";
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Atendimento excluído com sucesso";
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION = ATTENDANCE_CONTROLLER_CREATE_403_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION = ATTENDANCE_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
	public static final String ATTENDANCE_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION = ATTENDANCE_CONTROLLER_CREATE_409_DESCRIPTION;
	//findAllByClient method
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_SUMMARY = "Listar Atendimentos por Cliente";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_DESCRIPTION = "Lista de atendimentos filtrados por cliente";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_200_DESCRIPTION = "Retorna lista paginada de atendimentos filtrados por cliente";
	//findAllByVet method
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_OPERATION_SUMMARY = "Listar Atendimentos por Veterinário";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_OPERATION_DESCRIPTION = "Lista de atendimentos filtrados por veterinário";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_200_DESCRIPTION = "Retorna lista paginada de atendimentos filtrados por veterinário";
	//findAllByVet method
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_OPERATION_SUMMARY = "Listar Atendimentos por Cachorro";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_OPERATION_DESCRIPTION = "Lista de atendimentos filtrados por cachorro";
	public static final String ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_200_DESCRIPTION = "Retorna lista paginada de atendimentos filtrados por cachorro";
	
	private ControllersAnnotationsV1() {
		throw new IllegalAccessError("Utility Class");
	}
}
