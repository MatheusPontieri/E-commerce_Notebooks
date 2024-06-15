package br.notelab.service.pessoa.cliente;

import java.util.ArrayList;
import java.util.List;

import br.notelab.dto.endereco.EnderecoDTO;
import br.notelab.dto.pessoa.cliente.ClienteDTO;
import br.notelab.dto.pessoa.cliente.ClienteResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.dto.pessoa.usuario.EmailPatchDTO;
import br.notelab.dto.pessoa.usuario.SenhaPatchDTO;
import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import br.notelab.model.endereco.Endereco;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.pessoa.Cliente;
import br.notelab.model.pessoa.Pessoa;
import br.notelab.model.pessoa.Sexo;
import br.notelab.model.pessoa.Usuario;
import br.notelab.repository.CidadeRepository;
import br.notelab.repository.ClienteRepository;
import br.notelab.repository.NotebookRepository;
import br.notelab.repository.PessoaRepository;
import br.notelab.repository.UsuarioRepository;
import br.notelab.service.hash.HashService;
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

    @Inject
    public NotebookRepository notebookRepository;

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO dto) {
        validarEmailCliente(dto.email(), 0L);
        validarCpfCliente(dto.cpf(), 0L);
        validarListaTelefoneCliente(dto.telefones(), 0L);

        Usuario u = new Usuario();
        u.setEmail(dto.email());
        u.setSenha(hashService.getHashSenha(dto.senha()));
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
        validarListaTelefoneCliente(dto.telefones(), c.getPessoa().getId());

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
    @Transactional
    public void updateEmail(Long id, @Valid EmailPatchDTO dto) {
        Cliente c = clienteRepository.findById(id);
        Usuario u = c.getPessoa().getUsuario();

        u.setEmail(dto.email());
    }

    @Override
    @Transactional
    public void updateSenha(Long id, @Valid SenhaPatchDTO dto) {
        Cliente c = clienteRepository.findById(id);
        Usuario u = c.getPessoa().getUsuario();

        u.setSenha(hashService.getHashSenha(dto.senha()));
    }

    @Override
    @Transactional
    public void adicionarItemDesejo(Long id, Long idNotebook) {
        Cliente c = clienteRepository.findById(id);
        List<Notebook> listaDesejo = c.getListaDesejo();

        if(c.getListaDesejo() == null)
            listaDesejo = new ArrayList<>();
        
        else
            listaDesejo = c.getListaDesejo();
        
        listaDesejo.add(notebookRepository.findById(idNotebook));
    }

    @Override
    @Transactional
    public void removerItemDesejo(Long id, Long idNotebook) {
        Cliente c = clienteRepository.findById(id);
        List<Notebook> listaDesejo = c.getListaDesejo();

        if(listaDesejo == null)
            throw new ValidationException("listaDesejo", "Não há notebook para ser removido!");
                
        listaDesejo.remove(notebookRepository.findById(idNotebook));
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
        p.setListaTelefone(dto.telefones().stream().map(TelefoneDTO::convertToTelefone).toList());
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

        p.getListaTelefone().clear();
        dto.telefones().forEach(t -> p.getListaTelefone().add(TelefoneDTO.convertToTelefone(t)));

        p.getListaEndereco().clear();
        dto.enderecos().forEach(e -> p.getListaEndereco().add(convertToEndereco(e)));
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

    private void validarListaTelefoneCliente(List<TelefoneDTO> lista, Long id){
        for (TelefoneDTO telefone : lista) {
            if(pessoaRepository.findByTelefoneCompleto(telefone.codigoArea(), telefone.numero(), id) != null)
                throw new ValidationException("telefone", "O telefone "+ telefone.codigoArea().concat(telefone.numero()) +" já existe");
        }
    }
}