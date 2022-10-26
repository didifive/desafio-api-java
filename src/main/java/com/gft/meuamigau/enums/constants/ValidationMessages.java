package com.gft.meuamigau.enums.constants;

public final class ValidationMessages {
	
	/**
	 * Constants for Validation Messages
	 */
	public static final String DEVE_CONTER_8_CARACTERES = "Deve conter 8 caracteres";
	public static final String DEVE_CONTER_10_CARACTERES = "Deve conter 10 caracteres";
	public static final String DEVE_CONTER_19_CARACTERES = "Deve conter 19 caracteres";
	public static final String NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES = "Não pode conter mais que 10 caracteres";
	public static final String NAO_PODE_CONTER_MAIS_QUE_15_CARACTERES = "Não pode conter mais que 15 caracteres";
	public static final String NAO_PODE_CONTER_MAIS_QUE_45_CARACTERES = "Não pode conter mais que 45 caracteres";
	public static final String NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES = "Não pode conter mais que 255 caracteres";
	public static final String NAO_PODE_CONTER_MAIS_QUE_65535_CARACTERES = "Não pode conter mais que 65535 caracteres";
	public static final String NAO_PODE_CONTER_MAIS_QUE_16777215_CARACTERES = "Não pode conter mais que 16777215 caracteres";
	
	/**
	 * Constants for Validation Messages in specifics fiels in RecordPersonDTO
	 */
	public static final String ID_NAO_PODE_SER_VAZIO = "Id não pode ser vazio";
	public static final String NAME_NAO_PODE_SER_VAZIO = "Nome não pode ser vazio";
	public static final String PERSON_TYPE_NAO_PODE_SER_VAZIO = "Tipo de Pessoa não pode ser vazio";
	public static final String EMAIL_NAO_PODE_SER_VAZIO = "E-mail não pode ser vazio";
	public static final String CPF_CNPJ_NAO_PODE_SER_VAZIO = "Deve ser informado um CPF ou CNPJ válido";
	public static final String DEVE_INFORMAR_UM_CPF_VALIDO = "CPF informado está inválido para tipo de Pessoa Física";
	public static final String DEVE_INFORMAR_UM_CNPJ_VALIDO = "CNPJ informado está inválido para tipo de Pessoa Jurídica";
	public static final String DEVE_INFORMAR_EMAIL_VALIDO = "E-mail informado está inválido";
	public static final String SPECIALITY_NAO_PODE_SER_VAZIO = "Especialidade não pode ser vazio";
	public static final String CRMV_NAO_PODE_SER_VAZIO = "CRMV não pode ser vazio";
	public static final String CRMV_UF_NAO_PODE_SER_VAZIO = "CRMV UF não pode ser vazio";
	/**
	 * Constants for Validation Messages in specifics fiels of RecordAddressDTO
	 */
	public static final String PUBLIC_PLACE_NAO_PODE_SER_VAZIO = "publicPlace não pode ser vazio";
	public static final String ADDRESS_TYPE_NAO_PODE_SER_VAZIO = "addressType não pode ser vazio";
	public static final String NUMBER_NAO_PODE_SER_VAZIO = "number não pode ser vazio";
	public static final String ZIP_CODE_NAO_PODE_SER_VAZIO = "zipCode não pode ser vazio";
	/**
	 * Constants for Validation Messages in specifics fiels of RecordUserDTO
	 */
	public static final String USERNAME_INFORMADO_DEVE_TER_MINIMO_DE_2_E_MAXIMO_DE_100_CARACTERES = "username informado deve ter mínimo de 2 e máximo de 100 caracteres";
	public static final String PASSWORD_INFORMADO_DEVE_TER_MINIMO_DE_6_E_MAXIMO_DE_25_CARACTERES = "password informado deve ter mínimo de 6 e máximo de 25 caracteres";
	public static final String PASSWORD_DEVE_SER_INFORMADO = "password deve ser informado";
	public static final String USERNAME_DEVE_SER_INFORMADO = "username deve ser informado";
	
	private ValidationMessages() {
		throw new IllegalAccessError("Utility Class");
	}

}
