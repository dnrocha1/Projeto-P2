package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FeedNoticias {

	private Map<Integer, ArrayList<Postagem>> feedsParaAtualizar = new HashMap<Integer, ArrayList<Postagem>>();
	private Map<Integer, Comparator<Postagem>> comparadores = new HashMap<Integer, Comparator<Postagem>>();

	private ArrayList<Postagem> feedExibidoPorData = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedExibidoPorPop = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedAtualizadoPorData = new ArrayList<Postagem>();
	private ArrayList<Postagem> feedAtualizadoPorPop = new ArrayList<Postagem>();

	public FeedNoticias() {
		this.feedsParaAtualizar.put(0, this.feedAtualizadoPorPop);
		this.feedsParaAtualizar.put(1, this.feedAtualizadoPorData);
		this.comparadores.put(0, compararPops);
		this.comparadores.put(1, compararData);
	}

	public void atualizaFeed(ArrayList<Usuario> amigos) {
		for (Usuario amigo : amigos) {
			@SuppressWarnings("unchecked")
			ArrayList<Postagem> muralAmigo = (ArrayList<Postagem>) amigo.mural.clone();
			if (amigo.getPopularidade().equals("Normal Pop")) {
				for (int i = 0; i < 2; i++) {
					Collections.sort(muralAmigo, comparadores.get(i));
					for (int j = 0; j < muralAmigo.size() && j < 2; j++) {
						this.feedsParaAtualizar.get(i).add(muralAmigo.get(j));
					}
				}
			} else if (amigo.getPopularidade().equals("Celebridade Pop")) {
				for (int i = 0; i < 2; i++) {
					Collections.sort(muralAmigo, comparadores.get(i));
					for (int j = 0; j < muralAmigo.size() && j < 4; j++) {
						this.feedsParaAtualizar.get(i).add(muralAmigo.get(j));
					}
				}
			} else if (amigo.getPopularidade().equals("Icone Pop")) {
				for (int i = 0; i < 2; i++) {
					Collections.sort(muralAmigo, comparadores.get(i));
					for (int j = 0; j < muralAmigo.size() && j < 6; j++) {
						this.feedsParaAtualizar.get(i).add(muralAmigo.get(j));
					}
				}
			}

		}
		Collections.sort(feedAtualizadoPorData, compararData);
		Collections.sort(feedAtualizadoPorPop, compararPops);
		this.feedExibidoPorData.addAll(feedAtualizadoPorData);
		this.feedExibidoPorPop.addAll(feedAtualizadoPorPop);

	}

	Comparator<Postagem> compararData = new Comparator<Postagem>() {

		@Override
		public int compare(Postagem postagem, Postagem outraPostagem) {
			try {
				return outraPostagem.getData().compareTo(postagem.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	};

	Comparator<Postagem> compararPops = new Comparator<Postagem>() {

		@Override
		public int compare(Postagem postagem, Postagem outraPostagem) {
			if (postagem.getPops() > outraPostagem.getPops()) {
				return -1;
			} else if (postagem.getPops() < outraPostagem.getPops()) {
				return 1;
			} else {
				return compararData.compare(postagem, outraPostagem);
			}

		}
	};

	public Postagem getFeedPopularidade(int post) {
		return this.feedExibidoPorPop.get(this.feedExibidoPorPop.size() - post - 1);
	}

	public Postagem getFeedTempo(int post) {
		return this.feedExibidoPorData.get(this.feedExibidoPorData.size() - post - 1);
	}
}
