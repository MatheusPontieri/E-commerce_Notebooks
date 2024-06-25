package br.notelab.service.pessoa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.notelab.model.file.FileInfo;
import br.notelab.model.pessoa.Pessoa;
import br.notelab.repository.PessoaRepository;
import br.notelab.service.file.FileService;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PessoaFileServiceImpl implements FileService {

    @Inject
    public PessoaRepository pessoaRepository;

    private static final String USER_PATH = System.getProperty("user.home")
        + File.separator + "quarkus"
        + File.separator + "images"
        + File.separator + "pessoa" + File.separator;

    @Override
    @Transactional
    public void upload(Long id, String nomeImagem, byte[] imagem) {
        Pessoa p = pessoaRepository.findById(id);
        List<FileInfo> listaNomeImagem = p.getListaNomeImagem();

        if (listaNomeImagem == null) { 
            listaNomeImagem = new ArrayList<>();
            p.setListaNomeImagem(listaNomeImagem);
        }   

        try {
            FileInfo i = new FileInfo();
            i.setFileName(salvarImagem(nomeImagem, imagem));

            listaNomeImagem.add(i);
        } catch (IOException e){
            throw new ValidationException("imagem", e.getMessage());
        }
    }

    @Override
    public File download(String nomeImagem) {
        return new File(USER_PATH + nomeImagem);
    }

    private String salvarImagem(String nomeImagem, byte[] imagem) throws IOException{
        String formato = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listaFormatos = Arrays.asList("image/jpg", "image/gif",  "image/png",  "image/jpeg");

        if (!listaFormatos.contains(formato)) 
            throw new IOException("Tipo de imagem não suportada");

        if (imagem.length > 1024 * 1024 * 10) 
            throw new IOException("Imagem muito grande, tamanho máximo de 10 MB");

        Path diretorioImagens = Path.of(USER_PATH);
        Files.createDirectories(diretorioImagens);

        String nomeArquivo = UUID.randomUUID() + "." + formato.substring(formato.lastIndexOf("/") + 1);
        String path = USER_PATH + nomeArquivo;

        File file = new File(path);
        if (file.exists())
            throw new IOException("Esta imagem já existe!");

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        fos.flush();
        fos.close();

        return nomeArquivo;
    }
}