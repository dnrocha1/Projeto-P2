/**
 * Projeto LP2 - 2014.2
 * @author Daniyel Rocha 114210779
 * @author Igor Pinheiro 114210164
 * @author Matheus Maia 114210417
 * 
 * Classe que eh uma itermediadora entre a interface grafica e o controller e todos os metodos dessa classe farao forwarding para a classe Controller.
 */
package core;

import exceptions.ErroAtualizacao;
import exceptions.ErroCadastro;
import exceptions.ErroDeEntrada;
import exceptions.ErroLogin;
import exceptions.ErroUsuarioOffline;

public class Facade {

	Controller controle = new Controller();
	Controller sistema = null;

	public void iniciaSistema() {
		sistema = controle;
	}

	public void fechaSistema() throws Exception {
		if (sistema.getUsuarioLogado() != null) {
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}

		else {
			this.sistema = null;
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor) throws Exception {
		if (sistema.getUsuarioLogado() == null) {
			throw new ErroUsuarioOffline("Nao eh possivel atualizar um perfil. ");
		}
		try {
			sistema.atualizaPerfil(nomeInformacao, valor);
		} catch (Exception e) {
			throw new ErroAtualizacao(e.getMessage());
		}
	}

	public void atualizaPerfil(String nomeInformacao, String valor, String velhaSenha) throws Exception {
		if (sistema.getUsuarioLogado() == null) {
			throw new ErroUsuarioOffline("Nao eh possivel atualizar um perfil. ");
		}
		try {
			sistema.atualizaPerfil(nomeInformacao, valor, velhaSenha);
		} catch (Exception e) {
			throw new ErroAtualizacao(e.getMessage());
		}
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario,
			String imgAvatar) throws Exception {
		try {
			sistema.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario, imgAvatar);
		} catch (Exception e) {
			throw new ErroCadastro(e.getMessage());
		}
		return emailUsuario;
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario, String senhaUsuario, String dataNasUsuario)
			throws Exception {
		try {
			sistema.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario, dataNasUsuario);
			return emailUsuario;
		} catch (Exception e) {
			throw new ErroCadastro(e.getMessage());
		}
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario) throws Exception {
		return sistema.bancodedados.getInfoUsuario(nomeInformacao, emailUsuario);

	}

	public void adicionaAmigo(String email) {
		sistema.adicionaAmigo(email);
	}

	public void removeAmigo(String email) {
		sistema.removeAmigo(email);
	}

	public void aceitaAmizade(String email) throws Exception {
		sistema.aceitaAmizade(email);
	}

	public void rejeitaAmizade(String email) throws Exception {
		sistema.rejeitaAmizade(email);
	}

	public int getNotificacoes() {
		return sistema.getNotificacao();

	}

	public int getQtdAmigos() {
		return sistema.getQtdAmigos();
	}

	public String getNextNotificacao() throws Exception {
		return sistema.getNextInformacao();
	}

	public String getInfoUsuario(String nomeInformacao) throws Exception {
		return sistema.getInfoUsuarioLogado(nomeInformacao);
	}

	public void login(String emailUsuario, String senhaUsuario) throws Exception {
		try {
			sistema.login(emailUsuario, senhaUsuario);
		} catch (Exception e) {
			throw new ErroLogin(e.getMessage());
		}
	}

	public void logout() throws Exception {
		sistema.logout();

	}

	public void removeUsuario(String email) {
		sistema.removeUsuario(email);
	}

	public Usuario buscaUsuario(String emailUsuario) throws Exception {
		return sistema.bancodedados.buscaUsuario(emailUsuario);

	}

	public void criaPost(String conteudo, String data) throws Exception {
		sistema.postarMensagem(conteudo, data);
	}

	public String getPost(int indice) throws Exception {
		return sistema.getPost(indice);
	}

	public String getPost(String atributo, int indice) throws Exception {
		return sistema.getPost(atributo, indice);
	}

	public int getTotalPosts() {
		return sistema.getTotalPosts();
	}

	public void curtirPost(String emailAmigo, int indicePost) throws Exception {
		sistema.curtirPost(emailAmigo, indicePost);
	}

	public String getConteudoPost(int indice, int post) throws Exception {
		return sistema.getConteudo(indice, post);
	}

	public String atualizaRanking() {
		return sistema.atualizaRanking();
	}

	public void adicionaPops(int pops) {
		sistema.adicionaPops(pops);
	}

	public String getPopularidade() {
		return sistema.getPopularidade();
	}

	public void rejeitarPost(String emailUsuario, int post) throws Exception {
		sistema.rejeitarPost(emailUsuario, post);
	}

	public int getPopsPost(int post) {
		return sistema.getPopsPost(post);
	}

	public int qtdCurtidasDePost(int post) throws Exception {
		return sistema.qtdCurtidasDePost(post);
	}

	public int qtdRejeicoesDePost(int post) throws Exception {
		return sistema.qtdDescurtidasDePost(post);
	}

	public int getPopsUsuario(String emailUsuario) throws Exception {
		return sistema.getPopsUsuario(emailUsuario);
	}

	public int getPopsUsuario() throws Exception {
		return sistema.getPopsUsuario();
	}

	public String atualizaTrendingTopics() {
		return sistema.atualizaTrendingTopics();
	}

	public String atualizaRankings() {
		return sistema.atualizaRanking();
	}

	public Postagem getPostFeedNoticiasRecentes(int post) throws ErroDeEntrada {
		return this.sistema.getPostFeedNoticiasRecentes(post);
	}

	public Postagem getPostFeedNoticiasMaisPopulares(int post) throws ErroDeEntrada {
		return this.sistema.getPostFeedNoticiasMaisPopulares(post);

	}

	public void atualizaFeed() {
		this.sistema.atualizaFeed();

	}

	public void baixaPosts() throws Exception {
		this.sistema.exportaPostagem();
	}

}
