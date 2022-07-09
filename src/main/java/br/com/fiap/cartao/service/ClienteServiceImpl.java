package br.com.fiap.cartao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cartao.dto.ClienteDTO;
import br.com.fiap.cartao.dto.CreateUpdateClienteDTO;
import br.com.fiap.cartao.dto.LimiteDisponivelClienteDTO;
import br.com.fiap.cartao.entity.Cliente;
import br.com.fiap.cartao.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public ClienteDTO create(CreateUpdateClienteDTO createUpdateClienteDTO) {
		Cliente cliente = 
				new Cliente(createUpdateClienteDTO.getNome(), 
							createUpdateClienteDTO.getMatricula());
		Cliente clienteGravado = clienteRepository.save(cliente);
		return new ClienteDTO(clienteGravado);
	}

	@Override
	public ClienteDTO update(Long id, CreateUpdateClienteDTO createUpdateClienteDTO) {
		Cliente cliente = clienteRepository.findById(id)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		cliente.setNome(createUpdateClienteDTO.getNome());
		cliente.setMatricula(createUpdateClienteDTO.getMatricula());
		Cliente clienteAlterado = clienteRepository.save(cliente);
		return new ClienteDTO(clienteAlterado);
	}

	@Override
	public ClienteDTO updateLimite(Long id, LimiteDisponivelClienteDTO limiteDisponivelClienteDTO) {
		Cliente cliente = clienteRepository.findById(id)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		cliente.setLimiteDisponivel(limiteDisponivelClienteDTO.getLimite());
		Cliente clienteAlterado = clienteRepository.save(cliente);
		return new ClienteDTO(clienteAlterado);
	}

	@Override
	public void delete(Long id) {
		Cliente cliente = clienteRepository.findById(id)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		clienteRepository.delete(cliente);
	}

	@Override
	public ClienteDTO findbyId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new ClienteDTO(cliente);
	}

	@Override
	public List<ClienteDTO> listAll(String nome) {
		List<Cliente> listaClientes; 
		if (nome == null) {
			listaClientes = clienteRepository.findAll();
		} else {
			listaClientes = clienteRepository.findAllByNomeContaining(nome);
		}
		return listaClientes.stream()
					.map(cliente -> new ClienteDTO(cliente))
					.collect(Collectors.toList());
	}

}
