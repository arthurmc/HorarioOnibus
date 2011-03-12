package br.com.onibus.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.onibus.models.Cidade;
import br.com.onibus.models.Horario;
import br.com.onibus.models.Linha;
import br.com.onibus.repositories.CidadeRepository;
import br.com.onibus.repositories.HorarioRepository;
import br.com.onibus.repositories.LinhaRepository;

import com.googlecode.objectify.Key;

public class LinhaServiceImplTest {

	private static CidadeRepository cidadeRepo;
	private static LinhaRepository linhaRepo;
	private static HorarioRepository horarioRepo;
	private static LinhaServiceImpl linhaDomain;

	@BeforeClass
	public static void inicia() {
		cidadeRepo = Mockito.mock(CidadeRepository.class);
		linhaRepo = Mockito.mock(LinhaRepository.class);
		horarioRepo = Mockito.mock(HorarioRepository.class);
		
		linhaDomain = new LinhaServiceImpl(linhaRepo, cidadeRepo, horarioRepo);
	}
	
	@Test
	public void deveCriarNovaLinha() {
		Cidade origem = new Cidade();
		origem.setNome("Foz do Iguaçu");
		
		Cidade destino = new Cidade();
		destino.setNome("Paraná");
		
		Mockito.when(cidadeRepo.exist(origem)).thenReturn(true);
		Mockito.when(cidadeRepo.exist(destino)).thenReturn(true);
		Mockito.when(cidadeRepo.find(origem.getId())).thenReturn(origem);
		Mockito.when(cidadeRepo.find(destino.getId())).thenReturn(destino);
		
		linhaDomain.criaNovaLinha(origem, destino);
	}

	@Test(expected=IllegalArgumentException.class)
	public void deveImpedirDeCriarALinhaCasoNaoExistaOrigemOuDestino() {	
		Cidade origem = new Cidade();	
		origem.setNome("Mogi Mirim");	
			
		Cidade destino = new Cidade();	
		destino.setNome("Araraquara");				Mockito.when(cidadeRepo.exist(origem)).thenReturn(false);		Mockito.when(cidadeRepo.exist(destino)).thenReturn(true);				linhaDomain.criaNovaLinha(origem, destino);	}	@Test(expected=IllegalArgumentException.class)	public void deveImpedirDeCriarALinhaCasoCidadeDeDestinoSejaIgualADeOrigem() {		Cidade origem = new Cidade();		origem.setNome("Mogi Mirim");				Cidade destino = new Cidade();		destino.setNome("Mogi Mirim");				Mockito.when(cidadeRepo.exist(origem)).thenReturn(true);		Mockito.when(cidadeRepo.exist(destino)).thenReturn(true);				linhaDomain.criaNovaLinha(origem, destino);	}
	
    @Test(expected=IllegalArgumentException.class)
    public void naoDeveDeixarAdicionarLinhaJaExistente() {
    	Cidade origemJardimInga = new Cidade();
    	origemJardimInga.setNome("Jardim Ingá");
    	
    	Cidade destinoAltoParaiso = new Cidade();
    	destinoAltoParaiso.setNome("Alto Paraíso");
    	
    	Linha jardimIngaToAltoParaiso = new Linha();
    	Mockito.when(linhaRepo.exist(jardimIngaToAltoParaiso)).thenReturn(true);
    	
    	linhaDomain.criaNovaLinha(origemJardimInga, destinoAltoParaiso);
    }
  
    @Test(expected=IllegalArgumentException.class)
    public void naoDeveDeixarAdicionarLinhaSemOrigem() {
    	Cidade ceuAzul = new Cidade();
    	ceuAzul.setNome("Céu Azul");

    	linhaDomain.criaNovaLinha(null, ceuAzul);
	}
    
    @Test
    public void tamanhoDaListaDeHorariosInformadaDeveSerIguailListaSaida() throws ParseException {
    	Horario primeiroHorario = new Horario();
    	primeiroHorario.setId(98L);
    	
    	Horario segundoHorario = new Horario();
    	segundoHorario.setId(99L);

		primeiroHorario.setHora(new SimpleDateFormat("kk:mm").parse("11:00"));
		segundoHorario.setHora(new SimpleDateFormat("kk:mm").parse("14:00"));
    	
    	Key<Horario> keyPrimeiro = new Key<Horario>(Horario.class, 98L);
    	Key<Horario> keySegundo = new Key<Horario>(Horario.class, 99L);
    	
    	ArrayList<Key<Horario>> keys = new ArrayList<Key<Horario>>();
    	keys.add(keyPrimeiro);
    	keys.add(keySegundo);
    	
    	Linha linha = new Linha();
    	linha.addHorario(primeiroHorario);
    	linha.addHorario(segundoHorario);
    	
    	Mockito.when(horarioRepo.find(keyPrimeiro.getId())).thenReturn(primeiroHorario);
    	Mockito.when(horarioRepo.find(keySegundo.getId())).thenReturn(segundoHorario);
    	
    	List<Horario> listaDeHorarios = linhaDomain.recuperaHorariosPelaLinha(linha);
    	
    	Assert.assertEquals(listaDeHorarios.size(), keys.size());
    }
    
}