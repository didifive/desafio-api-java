package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.constants.ErrorMessages.ERRO_CONEXAO_THE_DOG_API;
import static com.gft.meuamigau.enums.constants.ErrorMessages.ERRO_HTTP_STATUS_NOT_200;
import static com.gft.meuamigau.enums.constants.ErrorMessages.RACA_NAO_ENCONTRADA_BY_ID;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.breed.BreedMapper;
import com.gft.meuamigau.dtos.breed.ImageBreedDTO;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.dtos.breed.RequestBreedByIdDTO;
import com.gft.meuamigau.dtos.breed.RequestBreedDTO;
import com.gft.meuamigau.entities.Breed;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.exceptions.ConnectionExternalApiException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.exceptions.MeuAmigAuException;
import com.gft.meuamigau.repositories.BreedRepository;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BreedService {
	
	private final BreedRepository breedRepository;

	public static final String HEADER_X_API_KEY = "x-api-key";
	
	public static final String URL_THE_DOG_API_V1 = "https://api.thedogapi.com/v1";
	
	public static final String URL_BREEDS = URL_THE_DOG_API_V1+"/breeds";
	
	public static final String URL_BREEDS_BY_NAME = URL_BREEDS+"/search";
	public static final String URL_BREEDS_BY_NAME_Q_PARAM = URL_BREEDS_BY_NAME+"?q=%s";
	
	public static final String URL_IMAGES = URL_THE_DOG_API_V1+"/images/%s";
	
	public static final String URL_BREED_BY_ID = URL_THE_DOG_API_V1+"/images/search?breed_id=%s";
	 
	@Value("${meu-amigau-api.the-dog-api.x-api-key}")
	private String theDogApiKey;
	 
	protected HttpResponse<String> requestInTheDogApi(String uri) {
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
	             .GET()
	             .timeout(Duration.ofSeconds(10))
	             .uri(URI.create(uri))
	             .header(HEADER_X_API_KEY, theDogApiKey)
	             .build();

	    HttpResponse<String> response = null;
		
	    try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new MeuAmigAuException(ERRO_CONEXAO_THE_DOG_API);
		}
		
	    if (response.statusCode() != 200)
	    	throw new ConnectionExternalApiException(ERRO_HTTP_STATUS_NOT_200, response.statusCode());
	    
		return response;
	}
	
	protected List<RequestBreedDTO> getBreedsList() {
		String uri = URL_BREEDS;
	    
	    HttpResponse<String> response = requestInTheDogApi(uri);
	    
	    return convertJsonToListRequestBreedDTO(response);
	}
	
	protected List<RequestBreedDTO> getBreedsListByName(String name) {
		String uri = String.format(URL_BREEDS_BY_NAME_Q_PARAM,name);
	    
	    HttpResponse<String> response = requestInTheDogApi(uri);
	    
	    return convertJsonToListRequestBreedDTO(response);
	}

	private List<RequestBreedDTO> convertJsonToListRequestBreedDTO(HttpResponse<String> response) {
		Gson gson = new Gson();
	    Type breedListType = new TypeToken<List<RequestBreedDTO>>(){}.getType();
	    String jsonInString = response.body();
	     
	    return gson.fromJson(jsonInString, breedListType);
	}
	
	private Page<QueryBreedDTO> returnPageList(Pageable pageable, List<QueryBreedDTO> dtos) {
		final int start = (int)pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), dtos.size());
		 
		return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
	}
	 
	 /**
	 * Find all breeds in TheDogAPI
	 * @param Pageable pageable
	 * @return Page<QueryBreedDTO>
	 */
	public Page<QueryBreedDTO> findAll(Pageable pageable) {
		List<RequestBreedDTO> requestBreedsList = this.getBreedsList();
		
		List<QueryBreedDTO> dtos = requestBreedsList.stream()
										.map(BreedMapper::toQueryDTO)
										.toList();
		
		return returnPageList(pageable, dtos);
	}
	
	 /**
	 * Find breeds by name in TheDogAPI
	 * @param String name
	 * @param Pageable pageable
	 * @return Page<QueryBreedDTO>
	 */
	public Page<QueryBreedDTO> findByName(String name, Pageable pageable) {
		List<RequestBreedDTO> requestBreedsList = this.getBreedsListByName(name);
		
		List<QueryBreedDTO> dtos = requestBreedsList.stream()
										.map(BreedMapper::toQueryDTO)
										.toList();
		 
		return returnPageList(pageable, dtos);
	}
	
	/**
	 * Find breed image
	 * @param String image
	 * @return ImageBreedDTO
	 */
	public ImageBreedDTO findImageById(String id) {
		String uri = String.format(URL_IMAGES,id);
		
		HttpResponse<String> response = requestInTheDogApi(uri);
		 
		Gson gson = new Gson();
	    String jsonInString = response.body();
	     
	    return gson.fromJson(jsonInString, ImageBreedDTO.class);
	}
	
	 /**
	 * Find breed by Id in TheDogAPI. If exists, automatically save it on base
	 * @param Long id
	 * @return EntityDtoBox<Breed, QueryBreedDTO>
	 */
	public EntityDtoBox<Breed, QueryBreedDTO> findInTheDogApiById(Long id, EntityDtoBoxType returnType) {
		String uri = String.format(URL_BREED_BY_ID,id);
		
		HttpResponse<String> response = requestInTheDogApi(uri);
		
		Gson gson = new Gson();
	    Type breedListType = new TypeToken<List<RequestBreedByIdDTO>>(){}.getType();
	    String jsonInString = response.body();
	     
	    List<RequestBreedByIdDTO> requestBreedsList = gson.fromJson(jsonInString, breedListType);
	    
	    if (requestBreedsList.isEmpty())
	    	throw new EntityNotFoundException(
	    			String.format(RACA_NAO_ENCONTRADA_BY_ID, id));
	    
	    RequestBreedDTO requestBreedDTO = requestBreedsList.get(0).getBreeds().get(0);
	    
	    if(returnType.equals(ENTITY))
	    	return new EntityDtoBox<>(this.create(requestBreedDTO, ENTITY).getEntity(),ENTITY);
	    
	    return new EntityDtoBox<>(this.create(requestBreedDTO), DTO);
	}
	/**
	 * Find breed by Id in TheDogAPI. If exists, automatically save it on base
	 * @param Long id
	 * @return QueryBreedDTO
	 */
	public QueryBreedDTO findInTheDogApiById(Long id) {
		return this.findInTheDogApiById(id, DTO).getDto();
	}
	
	 /**
	 * Find breed by Id in local base
	 * @param Long id
	 * @return QueryBreedDTO
	 */
	public QueryBreedDTO findById(Long id) {
		return findById(id, DTO).getDto();
	}
	/**
	 * Find breed by Id in local base
	 * @param Long id
	 * @param EntityDtoBoxType returnType
	 * @return EntityDtoBox<Breed, QueryBreedDTO>
	 */
	public EntityDtoBox<Breed, QueryBreedDTO> findById(Long id, EntityDtoBoxType returnType) {
		Optional<Breed> breed = breedRepository.findById(id);
		
		if(breed.isPresent()) {
			if (returnType.equals(ENTITY))
				return new EntityDtoBox<>(breed.get(), ENTITY);
				
			return new EntityDtoBox<>(BreedMapper.toQueryDTO(breed.get()), DTO);
		}
	    
	    return this.findInTheDogApiById(id, returnType);
	}
	
	
	/**
	 * Create breed with information of The Dog Api
	 * @param QueryBreedDTO dto
	 * @param EntityDtoBoxType returnType
	 * @return EntityDtoBox<Breed, QueryBreedDTO>
	 */
	public EntityDtoBox<Breed, QueryBreedDTO> create(RequestBreedDTO dto, EntityDtoBoxType returnType) {
		
		Breed breed = breedRepository.save(BreedMapper.toEntity(dto));
		
		if (returnType.equals(ENTITY))
			return new EntityDtoBox<>(breed, ENTITY);
		
		return new EntityDtoBox<>(BreedMapper.toQueryDTO(breed),DTO);
	}
	/**
	 * Create breed with information of The Dog Api
	 * @param QueryBreedDTO dto
	 * @return QueryBreedDTO
	 */
	public QueryBreedDTO create(RequestBreedDTO dto) {
		return this.create(dto,DTO).getDto();
	}
	 
}
