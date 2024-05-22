package br.notelab.service.pessoa.cliente;

import java.util.List;

import br.notelab.dto.endereco.EnderecoDTO;
import br.notelab.dto.pessoa.cliente.ClienteDTO;
import br.notelab.dto.pessoa.cliente.ClienteResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import br.notelab.model.endereco.Endereco;
import br.notelab.model.pessoa.Cliente;
import br.notelab.model.pessoa.Pessoa;
import br.notelab.model.pessoa.Sexo;
import br.notelab.model.pessoa.Telefone;
import br.notelab.model.pessoa.Usuario;
import br.notelab.repository.CidadeRepository;
import br.notelab.repository.ClienteRepository;
import br.notelab.repository.PessoaRepository;
import br.notelab.repository.UsuarioRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public PessoaRepository pessoaRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public CidadeRepository cidadeRepository;

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO dto) {
        validarEmailCliente(dto.email(), 0L);
        validarCpfCliente(dto.cpf(), 0L);
        // validarListaTelefoneCliente(dto.telefones().stream().map(TelefoneDTO::va), 0L);
        // validarUsuarioCliente();

        Usuario u = new Usuario();
        u.setEmail(dto.senha());
        u.setSenha(dto.senha());
        usuarioRepository.persist(u);

        Pessoa p = getPessoaFromClienteDTO(dto);
        p.setUsuario(u);
        pessoaRepository.persist(p);

        Cliente c = new Cliente();
        c.setAceitaMarketing(dto.aceitaMarketing());
        c.setPessoa(p);

        clienteRepository.persist(c);
        return ClienteResponseDTO.valueOf(c);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid ClienteDTO dto) {
        Cliente c = clienteRepository.findById(id);

        validarEmailCliente(dto.email(), c.getPessoa().getId());
        validarCpfCliente(dto.cpf(), c.getPessoa().getId());
        // validarTelefoneCliente(dto.telefone().codigoArea(), dto.telefone().numero(), c.getPessoa().getId());

        c.setAceitaMarketing(dto.aceitaMarketing());
        updateInstanceOfPessoa(c.getPessoa(), dto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Long idPessoa = clienteRepository.findById(id).getPessoa().getId(); 
        
        clienteRepository.deleteById(id);
        pessoaRepository.deleteById(idPessoa);
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        return ClienteResponseDTO.valueOf(clienteRepository.findById(id));
    }

    @Override
    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.listAll().stream().map(c -> ClienteResponseDTO.valueOf(c)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return clienteRepository.findByNome(nome).stream().map(c -> ClienteResponseDTO.valueOf(c)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).stream().map(c -> ClienteResponseDTO.valueOf(c)).toList();
    }

    
    @Override
    public UsuarioResponseDTO login(String email, String senha) {
        Cliente c = clienteRepository.findByEmailAndSenha(email, senha);
        return UsuarioResponseDTO.valueOf(c.getPessoa());
    }

    private Pessoa getPessoaFromClienteDTO(ClienteDTO dto){
        Pessoa p = new Pessoa();

        p.setNome(dto.nome());
        p.setDataNascimento(dto.dataNascimento());
        p.setCpf(dto.cpf());
       // p.setTelefone(TelefoneDTO.convertToTelefone(dto.telefone()));
        p.setSexo(Sexo.valueOf(dto.idSexo()));
        p.setListaEndereco(dto.enderecos().stream().map(e -> convertToEndereco(e)).toList());

        return p;
    }

    private void updateInstanceOfPessoa(Pessoa p, ClienteDTO dto){
        p.setNome(dto.nome());
        p.setDataNascimento(dto.dataNascimento());
        p.setCpf(dto.cpf());
        p.getUsuario().setEmail(dto.email());
        p.getUsuario().setSenha(dto.senha());
        p.setSexo(Sexo.valueOf(dto.idSexo()));

        p.getListaEndereco().clear();
        //dto.listaEndereco().forEach(e -> p.getListaEndereco().add(convertToEndereco(e)));

        //p.getTelefone().setNumero(dto.telefone().numero());
        //p.getTelefone().setCodigoArea(dto.telefone().codigoArea());
    }

    private Endereco convertToEndereco(EnderecoDTO dto){
        Endereco e = new Endereco();

        e.setCep(dto.cep());
        e.setLogradouro(dto.logradouro());
        e.setBairro(dto.bairro());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        e.setCidade(cidadeRepository.findById(dto.idCidade()));

        return e;
    }

    private void validarEmailCliente(String email, Long id){
        if(pessoaRepository.findByEmailCompleto(email, id) != null)
            throw new ValidationException("email", "O email "+email+" já existe");
    }

    private void validarCpfCliente(String cpf, Long id){
        if(pessoaRepository.findByCpfCompleto(cpf, id) != null)
            throw new ValidationException("cpf", "O cpf "+cpf+" já pertence a um cliente ou funcionário");
    }

    // private void validarListaTelefoneCliente(, Long id){
    //    if(pessoaRepository.findByTelefoneCompleto(codigoArea, numero, id) != null)
    //        throw new ValidationException("telefone", "O telefone "+ codigoArea.concat(numero) +" já existe");
    //}
}