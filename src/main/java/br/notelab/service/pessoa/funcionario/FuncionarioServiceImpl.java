package br.notelab.service.pessoa.funcionario;

import java.util.List;

import br.notelab.dto.endereco.EnderecoDTO;
import br.notelab.dto.pessoa.funcionario.FuncionarioDTO;
import br.notelab.dto.pessoa.funcionario.FuncionarioResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.dto.pessoa.usuario.EmailPatchDTO;
import br.notelab.dto.pessoa.usuario.SenhaPatchDTO;
import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import br.notelab.model.endereco.Endereco;
import br.notelab.model.pessoa.Pessoa;
import br.notelab.model.pessoa.Sexo;
import br.notelab.model.pessoa.Usuario;
import br.notelab.model.pessoa.funcionario.Funcionario;
import br.notelab.model.pessoa.funcionario.Perfil;
import br.notelab.repository.CidadeRepository;
import br.notelab.repository.FuncionarioRepository;
import br.notelab.repository.PessoaRepository;
import br.notelab.repository.UsuarioRepository;
import br.notelab.service.hash.HashService;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    public FuncionarioRepository funcionarioRepository;

    @Inject
    public PessoaRepository pessoaRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public FuncionarioResponseDTO create(@Valid FuncionarioDTO dto) {
        validarEmailCliente(dto.email(), 0L);
        validarCpfCliente(dto.cpf(), 0L);
        validarListaTelefoneFuncionario(dto.telefones(), 0L);

        Usuario u = new Usuario();
        u.setEmail(dto.email());
        u.setSenha(hashService.getHashSenha(dto.senha()));
        usuarioRepository.persist(u);

        Pessoa p = getPessoaFromFuncionarioDTO(dto);
        p.setUsuario(u);
        pessoaRepository.persist(p);

        Funcionario f = new Funcionario();
        f.setSalario(dto.salario());
        f.setDataContrato(dto.dataContrato());
        f.setPessoa(p);
        f.setPerfil(Perfil.valueOf(dto.idPerfil()));

        funcionarioRepository.persist(f);
        return FuncionarioResponseDTO.valueOf(f);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid FuncionarioDTO dto) {
        Funcionario f = funcionarioRepository.findById(id);

        validarEmailCliente(dto.email(), f.getPessoa().getId());
        validarCpfCliente(dto.cpf(), f.getPessoa().getId());
        validarListaTelefoneFuncionario(dto.telefones(), f.getPessoa().getId());
        // validarUsuarioFuncionario()

        f.setSalario(dto.salario());
        f.setDataContrato(dto.dataContrato());
        updateInstanceOfPessoa(f.getPessoa(), dto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Long idPessoa = funcionarioRepository.findById(id).getPessoa().getId(); 
        
        funcionarioRepository.deleteById(id);
        pessoaRepository.deleteById(idPessoa);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, @Valid EmailPatchDTO dto) {
        Funcionario c = funcionarioRepository.findById(id);
        Usuario u = c.getPessoa().getUsuario();

        u.setEmail(dto.email());
    }

    @Override
    @Transactional
    public void updateSenha(Long id, @Valid SenhaPatchDTO dto) {
        Funcionario c = funcionarioRepository.findById(id);
        Usuario u = c.getPessoa().getUsuario();

        u.setSenha(hashService.getHashSenha(dto.senha()));
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        return FuncionarioResponseDTO.valueOf(funcionarioRepository.findById(id));
    }

    @Override
    public List<FuncionarioResponseDTO> findAll() {
        return funcionarioRepository.listAll().stream().map(f -> FuncionarioResponseDTO.valueOf(f)).toList();
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome).stream().map(f -> FuncionarioResponseDTO.valueOf(f)).toList();
    }

    @Override
    public List<FuncionarioResponseDTO> findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf).stream().map(f -> FuncionarioResponseDTO.valueOf(f)).toList();
    }

    @Override
    public UsuarioResponseDTO loginFuncionario(String email, String senha) {
        Funcionario f = funcionarioRepository.findFuncionarioByEmailAndSenha(email, senha);
        return UsuarioResponseDTO.valueOf(f.getPessoa());
    }

    @Override
    public UsuarioResponseDTO loginAdministrador(String email, String senha) {
        Funcionario f = funcionarioRepository.findAdministradorByEmailAndSenha(email, senha);
        return UsuarioResponseDTO.valueOf(f.getPessoa());
    }

    private Pessoa getPessoaFromFuncionarioDTO(FuncionarioDTO dto){
        Pessoa p = new Pessoa();

        p.setNome(dto.nome());
        p.setDataNascimento(dto.dataNascimento());
        p.setCpf(dto.cpf());
        p.setListaTelefone(dto.telefones().stream().map(TelefoneDTO::convertToTelefone).toList());
        p.setSexo(Sexo.valueOf(dto.idSexo()));
        p.setListaEndereco(dto.enderecos().stream().map(e -> convertToEndereco(e)).toList());

        return p;
    }

    private void updateInstanceOfPessoa(Pessoa p, FuncionarioDTO dto){
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

    private void validarListaTelefoneFuncionario(List<TelefoneDTO> lista, Long id){
        for (TelefoneDTO telefone : lista) {
            if(pessoaRepository.findByTelefoneCompleto(telefone.codigoArea(), telefone.numero(), id) != null)
                throw new ValidationException("telefone", "O telefone "+ telefone.codigoArea().concat(telefone.numero()) +" já existe");
        }
    }
}