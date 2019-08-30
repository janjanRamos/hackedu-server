/**
 * Data: 13 de jun de 2019
 */
package br.teresafernandes.evoluaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teresafernandes.evoluaserver.dominio.Pessoa;
import br.teresafernandes.evoluaserver.dominio.Usuario;
import br.teresafernandes.evoluaserver.exception.ServiceBusinessException;
import br.teresafernandes.evoluaserver.repo.PessoaRepository;
import br.teresafernandes.evoluaserver.repo.UsuarioRepository;
import br.teresafernandes.evoluaserver.util.MailUtil;
import br.teresafernandes.evoluaserver.util.StringUtils;
import br.teresafernandes.evoluaserver.util.ValidatorUtil;

/**
 * @author Teresa Fernandes
 *
 */
@CrossOrigin
@RestController
@RequestMapping({ "/usuario" })
public class UsuarioController extends AbstractController<Usuario>{
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	/**
	 * @param repository
	 */
	UsuarioController(UsuarioRepository repository) {
		super(repository);
	}
	
	public void validarAntesSalvar(Usuario obj) throws ServiceBusinessException {
		
		//valida dados obrigatórios
		if(obj.getPessoa() != null) {
			validarObrigatoriedade(obj.getPessoa().getNome(), "Nome");
			validarObrigatoriedade(obj.getPessoa().getCpf(), "CPF");
			validarObrigatoriedade(obj.getPessoa().getEmail(), "Email");
		}
		validarObrigatoriedade(obj.getSenha(), "Senha");
		validarObrigatoriedade(obj.getConfirmacaoSenha(), "Confirmação de Senha");
		checarErros();
		
		//valida se o usuário já existe
		Usuario usuarioBanco = ((UsuarioRepository) repository).findFirstByLoginAndAtivoIsTrue(obj.getPessoa().getEmail());
		if(ValidatorUtil.isNotEmpty(usuarioBanco)) {
			addErro("Já existe um usuário cadastrado com esses dados.");
			checarErros();
		}
		
		//valida dados pessoais cadastrados
		Pessoa pessoaBanco = pessoaRepository.findFirstByCpfAndAtivoIsTrue(obj.getPessoa().getCpf());
		if(ValidatorUtil.isEmpty(pessoaBanco)) {
			addErro("CPF não existe.");
		}else {
			if(pessoaBanco.getNome().compareTo(obj.getPessoa().getNome()) != 0) {
				addErro("Nome inválido.");
			}
			if(pessoaBanco.getEmail().compareTo(obj.getPessoa().getEmail()) != 0) {
				addErro("Email inválido.");
			}
			if(obj.getSenha().compareTo(obj.getConfirmacaoSenha()) != 0) {
				addErro("A confirmação da senha deve ser igual a senha.");
			}
		}
		checarErros();
		
		//o email corresponderá ao login do usuário
		obj.setPessoa(pessoaBanco);
		obj.setLogin(obj.getPessoa().getEmail());	
		obj.setSenha(StringUtils.toMD5(obj.getSenha()));
	}
	
	@PostMapping(path = { "/logar" })
	public ResponseEntity<Object> logar(@RequestBody Usuario usuario){
		try {
			limparErros();
			
			//valida o login e senha
			if(ValidatorUtil.isEmpty(usuario.getLogin()) || ValidatorUtil.isEmpty(usuario.getSenha())) {
				addErro("Usuário e/ou senha inválidos.");
				checarErros();
			}
			
			//consulta usuario com mesmo login
			Usuario usuarioBanco = ((UsuarioRepository) repository).findFirstByLoginAndAtivoIsTrue(usuario.getLogin());
			String senhaMd5 = StringUtils.toMD5(usuario.getSenha());
			if(ValidatorUtil.isEmpty(usuarioBanco)
					|| usuarioBanco.getSenha().compareTo(senhaMd5) != 0) {
				addErro("Usuário e/ou senha inválidos.");
				checarErros();
			}
			
			return ResponseEntity.ok().build();
		}catch (ServiceBusinessException e) {
			return new ResponseEntity<Object>(e, e.getStatus());
		}
	}
	
	@GetMapping(path = { "/redefinirSenha/{email}" })
	public ResponseEntity<Object> definirNovaSenha(@PathVariable String email) {
		try {

			limparErros();
			
			//valida email preenchido
			validarObrigatoriedade(email, "Email");
			checarErros();
			
			Usuario usuarioBanco = ((UsuarioRepository) repository).findFirstByLoginAndAtivoIsTrue(email);
			if(ValidatorUtil.isEmpty(usuarioBanco)) {
				addErro("Email inválido ou inexistente.");
				checarErros();
			}
			
			//envia email com nova senha
			String novaSenha = StringUtils.stringAleatoria();
			String assunto = "EVOLUA - Redefinição de senha";
			String mensagem = "Abaixo a sua nova senha: \n "+ novaSenha;
			MailUtil.enviarEmail(assunto, mensagem, email);
			
			//redefine senha
			usuarioBanco.setSenha(StringUtils.toMD5(novaSenha));
			repository.save(usuarioBanco);

			return ResponseEntity.ok().build();
		}catch (ServiceBusinessException e) {
			return new ResponseEntity<Object>(e, e.getStatus());
		}
	}
}
