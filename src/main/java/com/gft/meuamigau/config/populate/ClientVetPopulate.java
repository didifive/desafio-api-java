package com.gft.meuamigau.config.populate;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gft.meuamigau.dtos.client.QueryClientDTO;
import com.gft.meuamigau.dtos.client.RecordClientDTO;
import com.gft.meuamigau.dtos.vet.QueryVetDTO;
import com.gft.meuamigau.dtos.vet.RecordVetDTO;
import com.gft.meuamigau.services.ClientService;
import com.gft.meuamigau.services.VetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ClientVetPopulate {	
	
	private final ClientService clientService;
	
	private final VetService vetService;
	
	private static final String DATA = "25/07/2022";
	
	private static final RecordClientDTO CLIENT_1 = new RecordClientDTO(1L, DATA);
	private static final RecordClientDTO CLIENT_2 = new RecordClientDTO(2L, DATA);
	private static final RecordClientDTO CLIENT_3 = new RecordClientDTO(3L, DATA);

	private static final RecordVetDTO VET_1 = new RecordVetDTO(3L, DATA, "Clínico Geral", "2347489/5", "SP");
	private static final RecordVetDTO VET_2 = new RecordVetDTO(4L, DATA, "Castração", "24748935", "AM");
	

	public void createClients() {
		
		Pageable pageable = PageRequest.of(0, 10);
		Page<QueryClientDTO> queryClient = clientService.findAll(pageable);
		
		if(queryClient.getContent().isEmpty()) {
			List<RecordClientDTO> listaClientes = List.of(CLIENT_1, CLIENT_2, CLIENT_3);
			listaClientes.forEach(clientService::create);
		}
		
    }
	
	public void createVets() {
		
		Pageable pageable = PageRequest.of(0, 10);
		Page<QueryVetDTO> queryVet = vetService.findAll(pageable);
		
		if(queryVet.getContent().isEmpty()) {
			List<RecordVetDTO> listaVeterinarios = List.of(VET_1, VET_2);
			listaVeterinarios.forEach(vetService::create);
		}
		
    }
}