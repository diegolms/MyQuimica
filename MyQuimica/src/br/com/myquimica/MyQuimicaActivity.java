package br.com.myquimica;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseSineInOut;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.myquimica.core.FController;
import br.com.myquimica.models.GerenciadorMistura;
import br.com.myquimica.models.Jogador;
import br.com.myquimica.models.Mistura;




import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MyQuimicaActivity extends BaseGameActivity implements
IOnMenuItemClickListener, IOnAreaTouchListener{

	private static final int CAMERA_WIDTH = 1024;
	private static final int CAMERA_HEIGHT = 600;

	private final int MENU_MISTURAR = 0;
	private final int MENU_LIMPAR = MENU_MISTURAR + 1;
	private final int MENU_DICA = MENU_LIMPAR + 1;
	private final int MENU_PROXIMO = MENU_DICA + 1;

	private Camera camera;
	private BitmapTextureAtlas elementosTabelaTexture;

	private MenuScene menuScene;

	private Scene cena;
	private Font font;
	private Font fontDialogos;

	Texture fonteTexture;
	private Font fontScore;
	private ChangeableText textoPontos;

	private HashMap<Elementos, TextureRegion> mapaDeElementosDaTabela;


	private ChangeableText elementoQuimico;
	private ChangeableText formulaJogadorNaTela;
	private ChangeableText compostoQuimicoPedido;
	private BitmapTextureAtlas tuboTexture;
	private BitmapTextureAtlas sombraTexture;
	private TiledTextureRegion tuboDeEnsaioTexture;
	private TextureRegion sombra;
	private BitmapTextureAtlas balaoTexture;
	private TiledTextureRegion balaoDicaTexture, explosaoTextureRegion;

	private AnimatedSprite tuboDeEnsaio;
	private Sprite sombraDoMenu;

	private BitmapTextureAtlas quimicoTexture, fumacinhaTexture;
	private TiledTextureRegion quimicoTextureRegion, fumacinhaTextureRegion;

	private String formulaCompostaDoJogador;

	private PhysicsWorld physicsWorld;
	private static final FixtureDef FIXTURE_DEF = PhysicsFactory
			.createFixtureDef(1, 0.5f, 0.5f);
	protected static final int DICANATELA = 0;

	private GerenciadorMistura gm;

	private BitmapTextureAtlas cenarioTexture, explosaoTexture;
	private TextureRegion cenarioTextureRegion;
	private TextureRegion cenarioBancadaTextureRegion;

	private BitmapTextureAtlas menuTexture;
	private TextureRegion menuLimparTextureRegion;
	private TextureRegion menuMisturarTextureRegion;
	private TextureRegion menuDicaTextureRegion;
	private TextureRegion menuProximoTextureRegion;
	private BitmapTextureAtlas fontePontosTexture;
	private BitmapTextureAtlas fonteInfoBordas;
	private Font fontInformacoes;

	private String simboloDaVez, simboloAnterior, stringAux, s = "";
	private int contParaSimbolos = 0;
	private int contParaTotalDesafios = 1;
	private boolean primeiraVez = true;
	private boolean pediuDica = false;
	private Mistura misturaDaVez;
	private Mistura misturaAnterior;
	private Font fontDesafio;
	private BitmapTextureAtlas fonteDesafioTexture;
	private Text textoFormulaQuimica;
	private Text textoElemento;
	private int contDesafios;
	private Font fontMenu;
	private BitmapTextureAtlas fonteMenuTexture;
	private Font fontContDesafios;
	private BitmapTextureAtlas fonteContDesafiosTexture;
	private ChangeableText TextoContDesafios;
	private BitmapTextureAtlas cenarioBancadaTexture;

	private AnimatedSprite quimico, fumacinha;
	private final long[] DURACAO_ANIMACAO = new long[] { 100, 100, 100};
	private final long[] DURACAO_ANIMACAO_FUMACINHA = new long[] {120, 120, 120};
	private final long[] DURACAO_ANIMACAO_LIMPA_QUIMICO = new long[] {200,200};
	private final long[] DURACAO_ANIMACAO_EXPLOSAO = new long[] {500,500,500,500};
	private BitmapTextureAtlas tuboVerdeTexture;
	private TextureRegion tuboVerdeTextureRegion;
	private Line line;

	private BitmapTextureAtlas mao,elemento,elementoAuxTexture,
	balaoDeInformacoes,primeiroDialogo,segundoDialogo,terceiroDialogo,
	balaoDialogoTuboVazio,balaoDialogoRespostaErrada,botaoMisturarTexture,
	botaoLimparTexture,botaoProximoDesafioTexture;

	private TextureRegion maoTextureRegion, elementoTextureRegion,elementoAuxTextureRegion,
	balaoDeInformacoesTextureRegion,primeiroDialogoTextureRegion,botaoMisturarTextureRegion,
	botaoLimparTextureRegion,botaoProximoDesafioTextureRegion,segundoDialogoTextureRegion,
	terceiroDialogoTextureRegion,balaoDialogoTuboVazioTextureRegion,balaoDialogoRespostaErradaTextureRegion;


	private Sprite maozinha,maozinhaDireita,maozinhaEsquerda,botaoMisturar,botaoLimpar,botaoProximoDesafio;
	private Sprite elementoHid;
	private Sprite balaoAnimacaoDialogoQuimico,balaoDialogoQuimico,balaoDialogoErroEVazio;
	private Sprite elementoAux;

	private boolean acabouAnimacao = false;
	private boolean animacaoAuxiliar = false;
	private boolean pediuDicaUmaVez= false;
	private ChangeableText textoDialogoQuimico;
	private BitmapTextureAtlas fonteDialogoTexture;
	private Sprite sprite;

	private BitmapTextureAtlas maoDireita, maoEsquerda;
	private TextureRegion maoDireitaTextureRegion, balaoAnimacaoTextureRegion, maoEsquerdaTextureRegion;
	private BitmapTextureAtlas balaoAnimacao;
	private AnimatedSprite balao, explosao;

	private boolean controleMaozinhaDireita,controleMisturar,controleLimpar = false;
	Jogador j;
	private BitmapTextureAtlas dicaNormalTexture;
	private TextureRegion dicaNormalTextureRegion;
	private BitmapTextureAtlas interrogacao;
	private TextureRegion interrogacaoTextureRegion;
	private Sprite interrogacaoDica;


	public Engine onLoadEngine() {
		this.camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.camera);
		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		return new Engine(engineOptions);
	}

	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.fonteTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fonteContDesafiosTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fonteInfoBordas = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fonteDesafioTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fontePontosTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fonteMenuTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fonteDialogoTexture = new BitmapTextureAtlas(512, 512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		this.font = FontFactory.createFromAsset(this.fonteTexture, this, "font/arial.ttf", 25, true, Color.BLACK);
		this.fontDialogos = FontFactory.createFromAsset(this.fonteDialogoTexture, this, "font/arial.ttf", 20, true, Color.BLACK);
		this.fontContDesafios = FontFactory.createFromAsset(this.fonteDesafioTexture,this, "font/Archive.otf", 40, true, Color.rgb(248,19,13));
		this.fontScore = FontFactory.createFromAsset(this.fontePontosTexture,this, "font/dnk.ttf", 30, true, Color.BLACK);
		this.fontMenu = FontFactory.createFromAsset(this.fonteMenuTexture,this, "font/Plok.ttf", 80, true, Color.GRAY);
		this.fontInformacoes = FontFactory.createFromAsset(this.fonteInfoBordas,this, "font/Archive.otf", 30, true, Color.rgb(0,130,238));
		this.fontDesafio = FontFactory.createFromAsset(this.fonteDesafioTexture, this, "font/Plok.ttf", 20, true,Color.RED);

		this.cenarioTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.cenarioBancadaTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.explosaoTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.cenarioTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.cenarioTexture, this, "screen.png", 0, 0);
		this.cenarioBancadaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.cenarioBancadaTexture, this,"cenario.png", 0, 0);
		this.explosaoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.explosaoTexture, this,"telajogo_cenario_EXPLO.png", 0, 0, 1, 4);


		this.quimicoTexture = new BitmapTextureAtlas(2048, 512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.quimicoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.quimicoTexture, this,"quimicos.png", 0, 0, 6, 1);		

		this.tuboTexture = new BitmapTextureAtlas(1024,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.tuboDeEnsaioTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.tuboTexture, this, "Frasco_Bolhas_acerto.png", 0, 180, 3, 1);

		this.tuboVerdeTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.tuboVerdeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.tuboVerdeTexture, this, "telajogo_cenario_INSTRUMENTOS_03.png", 0,0);

		this.sombraTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.sombra = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.sombraTexture, this, "screen_black.png", 0, 0);

		this.balaoTexture = new BitmapTextureAtlas(512, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.balaoDicaTexture = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(balaoTexture, this, "bolhasDica.png", 0,0, 1, 4);
		this.balaoDicaTexture.setTextureRegionBufferManaged(false);

		this.elementosTabelaTexture = new BitmapTextureAtlas(1024, 512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.elementosTabelaTexture, this, "tabelaPeriodica.png", 0, 0);

		this.menuTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuMisturarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, this, "misturar2.png", 0, 0);
		this.menuLimparTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, this, "limpar2.png", 200, 50);
		this.menuDicaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, this, "dica2.png", 0, 100);
		this.menuProximoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuTexture, this, "prox_desafio2.png",0, 150);

		maoDireita = new BitmapTextureAtlas(256,256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mao = new BitmapTextureAtlas(256,256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		maoEsquerda = new BitmapTextureAtlas(256,256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		maoTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mao, this, "Maozinha.png", 0 ,0);
		maoDireitaTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(maoDireita, this, "MaozinhaDireita.png", 0 ,0);
		maoEsquerdaTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(maoEsquerda, this, "MaozinhaEsquerda.png", 0 ,0);

		interrogacao = new BitmapTextureAtlas(256,256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		interrogacaoTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(interrogacao, this, "interrogacao_1.png", 0 ,0);

		elemento = new BitmapTextureAtlas(64,64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		elementoTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(elemento, this, "elemento.png", 0 ,0);

		elementoAuxTexture = new BitmapTextureAtlas(64,64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		elementoAuxTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(elementoAuxTexture, this, "elementoAux.png", 0 ,0);

		this.balaoDeInformacoes = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		balaoDeInformacoesTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.balaoDeInformacoes, this, "telajogo_cenario_balaoInfoFormulas.png", 0 ,0);

		this.primeiroDialogo = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		primeiroDialogoTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.primeiroDialogo, this, "telajogo_cenario_balaoInfoFormulass.png", 0 ,0);

		this.segundoDialogo = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		segundoDialogoTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.segundoDialogo, this, "telajogo_cenario_balaoInfoFormulasss.png", 0 ,0);

		this.terceiroDialogo = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		terceiroDialogoTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.terceiroDialogo, this, "telajogo_cenario_balaoInfoFormulassss.png", 0 ,0);

		this.balaoDialogoTuboVazio = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		balaoDialogoTuboVazioTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.balaoDialogoTuboVazio, this, "telajogo_cenario_balaoTuboVazio.png", 0 ,0);

		this.balaoDialogoRespostaErrada = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.balaoDialogoRespostaErradaTextureRegion= BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.balaoDialogoRespostaErrada, this, "telajogo_cenario_balaoRepostaErrada.png", 0 ,0);

		this.fumacinhaTexture = new BitmapTextureAtlas(512, 128,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fumacinhaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.fumacinhaTexture, this,"fumacinha.png", 0, 0, 4, 1);		

		this.botaoMisturarTexture = new BitmapTextureAtlas(256, 64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.botaoMisturarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.botaoMisturarTexture, this,"telajogo_cenario_MENU_MISTURAR.png", 0, 0);

		this.botaoLimparTexture = new BitmapTextureAtlas(256, 64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.botaoLimparTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.botaoLimparTexture, this,"telajogo_cenario_MENU_LIMPAR.png", 0, 0);

		this.botaoProximoDesafioTexture = new BitmapTextureAtlas(512, 64,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.botaoProximoDesafioTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.botaoProximoDesafioTexture, this,"telajogo_cenario_MENU_PROX.png", 0, 0);

		this.dicaNormalTexture = new BitmapTextureAtlas(128, 128,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.dicaNormalTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.dicaNormalTexture, this,"dicaNormal.png", 0, 0);


		this.gm = new GerenciadorMistura();
		this.carregarMisturas();
		FController.getInstance().gravarLog("Iniciou o jogo em: " + new Date(), getApplicationContext());




		this.mapaDeElementosDaTabela = new HashMap<Elementos, TextureRegion>();

		for (final Elementos card : Elementos.values()) {
			final TextureRegion cardTextureRegion = TextureRegionFactory
					.extractFromTexture(this.elementosTabelaTexture,
							card.getTexturePositionX(),
							card.getTexturePositionY(), Elementos.CARD_WIDTH,
							Elementos.CARD_HEIGHT, true);
			this.mapaDeElementosDaTabela.put(card, cardTextureRegion);
		}

		this.mEngine.getTextureManager().loadTextures(this.cenarioTexture,
				this.cenarioBancadaTexture, this.explosaoTexture, this.fonteTexture,
				this.fonteInfoBordas, this.fonteDesafioTexture,
				this.fonteContDesafiosTexture, this.fontePontosTexture,
				this.fonteMenuTexture,// this.tuboTexture,
				this.tuboTexture,this.elementosTabelaTexture, this.quimicoTexture,this.tuboVerdeTexture,
				this.balaoTexture, this.menuTexture, this.sombraTexture, this.elemento,this.elementoAuxTexture,
				this.mao,this.maoDireita,maoEsquerda, this.balaoDeInformacoes,this.primeiroDialogo,this.segundoDialogo,
				this.terceiroDialogo,this.balaoDialogoTuboVazio,this.balaoDialogoRespostaErrada,this.fonteDialogoTexture,
				this.fumacinhaTexture,this.botaoMisturarTexture,botaoLimparTexture,botaoProximoDesafioTexture,this.dicaNormalTexture, this.interrogacao);
		this.mEngine.getFontManager().loadFonts(this.fontScore,
				this.fontInformacoes, this.fontDesafio, this.fontMenu,
				this.fontContDesafios,this.font, this.fontDialogos);

	}

	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.cena = new Scene();

		this.cena.setBackgroundEnabled(false);
		this.cena.attachChild(new Sprite(0, 0, this.cenarioTextureRegion));
		this.cena.attachChild(new Sprite(0, 0, this.cenarioBancadaTextureRegion));

		tuboDeEnsaio = new AnimatedSprite(440, 405, this.tuboDeEnsaioTexture);
		tuboDeEnsaio.animate(150);
		this.cena.attachChild(tuboDeEnsaio);


		this.carregarElementos();

		quimico = new AnimatedSprite(0, 250, this.quimicoTextureRegion);
		this.cena.attachChild(quimico);
		this.cena.attachChild(new Sprite(0, 380,this.tuboVerdeTextureRegion));


		explosao = new AnimatedSprite(28, 460, this.explosaoTextureRegion);

		this.animarQuimico();
		this.animarMaozinhaDireita();

		this.cena.setOnAreaTouchTraversalFrontToBack();
		this.cena.setTouchAreaBindingEnabled(true);

		this.physicsWorld = new PhysicsWorld(new Vector2(0,-SensorManager.GRAVITY_EARTH), false);
		this.cena.registerUpdateHandler(this.physicsWorld);
		this.cena.setOnAreaTouchListener(this);

		//this.createMenuScene();

		this.novoDesafio();
		return this.cena;
	}



	public void onLoadComplete() {

	}

	private void animarMaozinhaDireita(){
		balaoAnimacaoDialogoQuimico = new Sprite(55, 0, this.primeiroDialogoTextureRegion);
		maozinhaDireita = new Sprite(330,470, this.maoDireitaTextureRegion);
		maozinhaDireita.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new PathModifier(1f, new Path(2).to(330, 470).to(220,470), 
				EaseSineInOut.getInstance())), 5, new IEntityModifierListener(){

			public void onModifierStarted(IModifier<IEntity> arg0, IEntity arg1) {
				// TODO Auto-generated method stub

			}


			public void onModifierFinished(IModifier<IEntity> arg0, IEntity arg1) {
				mEngine.runOnUpdateThread(new Runnable() {

					@Override
					public void run() {
						cena.detachChild(maozinhaDireita);
						cena.detachChild(balaoAnimacaoDialogoQuimico);
						controleMaozinhaDireita = true;
						if(!animacaoAuxiliar){
							animarMaozinha();
							animacaoAuxiliar = true;
						}


					}

				});


			}
		}));
		this.cena.attachChild(maozinhaDireita);
		if(!animacaoAuxiliar){
			this.cena.attachChild(balaoAnimacaoDialogoQuimico);
		}




	}


	private void animarQuimico(){
		mEngine.registerUpdateHandler(new TimerHandler(5f, true,
				new ITimerCallback() {
			public void onTimePassed(final TimerHandler pTimerHandler) {
				quimico.animate(DURACAO_ANIMACAO, 0, 2, (int) (0+Math.random()*2),
						new IAnimationListener() {

					public void onAnimationEnd(
							AnimatedSprite pAnimatedSprite) {
						quimico.stopAnimation(0);

					}
				});
			}
		}));

	}

	private void animarMaozinha(){
		balaoAnimacaoDialogoQuimico = new Sprite(55, 0, this.segundoDialogoTextureRegion);
		maozinha = new Sprite(650,47, maoTextureRegion);
		elementoHid = new Sprite(645,44, elementoTextureRegion);

		//maozinha.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new PathModifier(3f, new Path(2).to(650, 47).to(515,465), 
		maozinha.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new PathModifier(3f, new Path(2).to(650, 47).to(520,500),
				EaseSineInOut.getInstance())), 2, new IEntityModifierListener() {


			public void onModifierStarted(IModifier<IEntity> arg0, IEntity arg1) {
				// TODO Auto-generated method stub

			}


			public void onModifierFinished(IModifier<IEntity> arg0, IEntity arg1) {
				mEngine.runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						cena.detachChild(maozinha);
						cena.detachChild(elementoHid);
						cena.detachChild(balaoAnimacaoDialogoQuimico);
						animarMaozinhaEsquerda();


					}

				});


			}
		}));
		//elementoHid.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new PathModifier(3f, new Path(2).to(645, 44).to(515,465),
		elementoHid.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new PathModifier(3f, new Path(2).to(645, 44).to(520,500),
				EaseSineInOut.getInstance())), 2));
		this.cena.attachChild(elementoHid);
		this.cena.attachChild(maozinha);
		this.cena.attachChild(balaoAnimacaoDialogoQuimico);


	}


	private void animarMaozinhaEsquerda(){
		balaoAnimacaoDialogoQuimico = new Sprite(55, 0, this.terceiroDialogoTextureRegion);
		//maozinhaEsquerda= new Sprite(700,400, maoEsquerdaTextureRegion);
		maozinhaEsquerda= new Sprite(700,420, maoDireitaTextureRegion);

		maozinhaEsquerda.registerEntityModifier(new LoopEntityModifier(new SequenceEntityModifier(new PathModifier(1f, new Path(2).to(870,430).to(650,430), 
				EaseSineInOut.getInstance())), 5, new IEntityModifierListener() {


			public void onModifierStarted(IModifier<IEntity> arg0, IEntity arg1) {
				// TODO Auto-generated method stub

			}


			public void onModifierFinished(IModifier<IEntity> arg0, IEntity arg1) {
				mEngine.runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						cena.detachChild(maozinhaEsquerda);
						cena.detachChild(balaoAnimacaoDialogoQuimico);
						cena.detachChild(botaoMisturar);
						cena.attachChild(botaoProximoDesafio);
						cena.attachChild(interrogacaoDica);
						cena.registerTouchArea(botaoProximoDesafio);
						cena.registerTouchArea(interrogacaoDica);
						//dicaNormal();
						acabouAnimacao = true;
					}

				});


			}
		}));
		this.cena.attachChild(maozinhaEsquerda);
		this.cena.attachChild(balaoAnimacaoDialogoQuimico);
		this.cena.attachChild(botaoMisturar);


	}

	private void animarFumacinha(){
		fumacinha= new AnimatedSprite(510, 350, this.fumacinhaTextureRegion);
		this.cena.attachChild(fumacinha);
		fumacinha.animate(DURACAO_ANIMACAO_FUMACINHA, 0, 2, 0,
				new IAnimationListener() {

			public void onAnimationEnd(
					AnimatedSprite pAnimatedSprite) {
				fumacinha.stopAnimation(3);	
				//cena.detachChild(fumacinha);

			}
		});
	}

	private void explosao(){
		explosao.animate(DURACAO_ANIMACAO_EXPLOSAO, 0, 3, 0,
				new IAnimationListener() {

			public void onAnimationEnd(
					AnimatedSprite pAnimatedSprite) {
				//fumacinha.stopAnimation(0);	


			}
		});

		this.cena.attachChild(explosao);
	}




	private void novoDesafio() {
		misturaDaVez = gm.gerarMistura();
		misturaAnterior = misturaDaVez;
		j = new Jogador(0);
		this.elementoQuimico = new ChangeableText(640, 560, this.font, "",HorizontalAlign.LEFT, 20);
		this.textoPontos = new ChangeableText(505, 20, this.fontScore, "Pontos: " +String.valueOf(j.getPontos()), "Pontos: XXXX".length());
		this.TextoContDesafios = new ChangeableText(20, 520,this.fontContDesafios, String.valueOf("DESAFIO " +this.contParaTotalDesafios) + "/5");
		this.formulaJogadorNaTela = new ChangeableText(827, 570, this.font, "",12);
		this.compostoQuimicoPedido = new ChangeableText(20, 560, this.font,	misturaDaVez.getNome(), 50)	;

		this.cena.attachChild(this.TextoContDesafios);
		this.cena.attachChild(formulaJogadorNaTela);
		this.cena.attachChild(compostoQuimicoPedido);
		this.cena.attachChild(elementoQuimico);
		this.cena.attachChild(this.textoPontos);
		//this.textoPontos.setPosition(534 - this.textoPontos.getWidth() / 2, 530);
		this.textoPontos.setPosition(534 - this.textoPontos.getWidth() / 2, 2);
		this.contDesafios = 0;
		this.formulaCompostaDoJogador = "";

		this.line = new Line(820, 540, 820 ,600,3);
		this.line.setColor(0,130,238);
		this.criarBotoes();



	}

	private void criarBotoes(){
		this.botaoMisturar= new Sprite(538, 485, this.botaoMisturarTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if(controleMisturar){
						misturar(formulaCompostaDoJogador);
						//explosao();
						controleMisturar = false;
					}
					break;
				}
				return true;


			}
		};

		this.botaoLimpar= new Sprite(379, 485, botaoLimparTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					zerar();
					break;
				}
				return true;


			}
		};


		this.botaoProximoDesafio = new Sprite(378, 545, botaoProximoDesafioTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if(controleMaozinhaDireita){
						proximoDesafio();
						controleMaozinhaDireita = false;
					}
					break;
				}
				return true;


			}
		};

		this.interrogacaoDica = new Sprite(933, 370, this.interrogacaoTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if (!pediuDica) {
						pediuDica = true;
						cena.detachChild(balaoDialogoErroEVazio);
						cena.detachChild(balaoDialogoQuimico);
						cena.detachChild(textoDialogoQuimico);
						pedirDica();
					}
					break;
				}
				return true;

			}
		};


	}



	private void comecar() {

		this.textoFormulaQuimica = new Text(827, 540, this.fontInformacoes,"F��rmula");
		this.cena.attachChild(textoFormulaQuimica);
		this.cena.attachChild(this.line);

	}

	private void comecarArrastar() {
		this.textoElemento = new Text(660, 540, this.fontInformacoes, "Elemento");
		this.cena.attachChild(textoElemento);

	}

	private void addElemento(final Elementos pCard, final int pX, final int pY) {


		final Sprite sprite = new Sprite(pX, pY, this.mapaDeElementosDaTabela.get(pCard)) {
			boolean mGrabbed = false;

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					if(acabouAnimacao){
						if (primeiraVez) {
							comecarArrastar();
						}
						elementoQuimico.setText(String.valueOf(pCard));
						elementoQuimico.setPosition(805 - font.getStringWidth(elementoQuimico.getText()), 570);
						simboloDaVez = String.valueOf(pCard);
						this.setScale(1.25f);
						this.mGrabbed = true;
						cena.detachChild(balaoDialogoQuimico);
						cena.detachChild(textoDialogoQuimico);
						cena.detachChild(balaoDialogoErroEVazio);

					}
					break;

				case TouchEvent.ACTION_MOVE:
					if (this.mGrabbed) {
						this.setPosition(pSceneTouchEvent.getX()- Elementos.CARD_WIDTH / 2,pSceneTouchEvent.getY() - Elementos.CARD_HEIGHT/ 2);

					}
					break;
				case TouchEvent.ACTION_UP:
					if (this.mGrabbed) {
						if (this.collidesWith(tuboDeEnsaio)) {
							animarFumacinha();
							cena.detachChild(botaoLimpar);
							cena.detachChild(botaoMisturar);
							cena.attachChild(botaoLimpar);
							cena.attachChild(botaoMisturar);
							cena.registerTouchArea(botaoLimpar);
							cena.registerTouchArea(botaoMisturar);
							controleMisturar = true;
							if (!simboloDaVez.equals(simboloAnterior)) {
								contParaSimbolos = 1;
								simboloAnterior = simboloDaVez;
								formulaCompostaDoJogador += pCard.formula;
								if (primeiraVez) {
									comecar();
									formulaJogadorNaTela.setText(formulaCompostaDoJogador);
									primeiraVez = false;


								} else {
									stringAux = formulaJogadorNaTela.getText();
									formulaJogadorNaTela.setText(stringAux+ pCard.formula);

								}



							} else {
								contParaSimbolos++;
								formulaCompostaDoJogador += pCard.formula;
								if (contParaSimbolos > 2) {
									stringAux = formulaJogadorNaTela.getText();
									formulaJogadorNaTela.setText(stringAux.substring(0,stringAux.length() - 1)+ String.valueOf(contParaSimbolos));
								} else {
									stringAux = formulaJogadorNaTela.getText();
									formulaJogadorNaTela.setText(stringAux+ String.valueOf(contParaSimbolos));
								}

							}

							if(elementoAux == null){
								cena.detachChild(elementoAux);
								elementoAux = new Sprite(520,500,this.getTextureRegion());
								cena.attachChild(elementoAux);
								elementoAux = null;
							}

						}
						this.setPosition(pX, pY);
						this.mGrabbed = false;
						this.setScale(1.0f);

					}
					break;

				}
				return true;
			}
		};

		this.cena.attachChild(sprite);
		this.cena.registerTouchArea(sprite);
	}


	private void carregarElementos() {
		// Linha 9
		this.addElemento(Elementos.Actínio, 209 + 6, 384 - 33);
		this.addElemento(Elementos.Tório, 252 + 6, 384 - 33);
		this.addElemento(Elementos.Protactínio, 295 + 6, 384 - 33);
		this.addElemento(Elementos.Urânio, 338 + 6, 384 - 33);
		this.addElemento(Elementos.Neptúnio, 381 + 6, 384 - 33);
		this.addElemento(Elementos.Plutônio, 424 + 6, 384 - 33);
		this.addElemento(Elementos.Amerício, 467 + 6, 384 - 33);
		this.addElemento(Elementos.Cúrio, 510 + 6, 384 - 33);
		this.addElemento(Elementos.Berquélio, 553 + 6, 384 - 33);
		this.addElemento(Elementos.Califórnio, 596 + 6, 384 - 33);
		this.addElemento(Elementos.Einstênio, 639 + 6, 384 - 33);
		this.addElemento(Elementos.Férmio, 682 + 6, 384 - 33);
		this.addElemento(Elementos.Mendelévio, 725 + 6, 384 - 33);
		this.addElemento(Elementos.Nobélio, 768 + 6, 384 - 33);
		this.addElemento(Elementos.Laurêncio, 811 + 6, 384 - 33);

		// Linha 8
		this.addElemento(Elementos.Lantânio, 209 + 6, 342 - 33);
		this.addElemento(Elementos.Cério, 252 + 6, 342 - 33);
		this.addElemento(Elementos.Praseodímio, 295 + 6, 342 - 33);
		this.addElemento(Elementos.Neodímio, 338 + 6, 342 - 33);
		this.addElemento(Elementos.Promécio, 381 + 6, 342 - 33);
		this.addElemento(Elementos.Samário, 424 + 6, 342 - 33);
		this.addElemento(Elementos.Európio, 467 + 6, 342 - 33);
		this.addElemento(Elementos.Gadolínio, 510 + 6, 342 - 33);
		this.addElemento(Elementos.Térbio, 553 + 6, 342 - 33);
		this.addElemento(Elementos.Disprósio, 596 + 6, 342 - 33);
		this.addElemento(Elementos.Hólmio, 639 + 6, 342 - 33);
		this.addElemento(Elementos.Érbio, 682 + 6, 342 - 33);
		this.addElemento(Elementos.Túlio, 725 + 6, 342 - 33);
		this.addElemento(Elementos.Itérbio, 768 + 6, 342 - 33);
		this.addElemento(Elementos.Lutécio, 811 + 6, 342 - 33);

		// Linha 7
		this.addElemento(Elementos.Frâncio, 123 + 6, 282 - 28);
		this.addElemento(Elementos.Rádio, 166 + 6, 282 - 28);
		this.addElemento(Elementos.Rutherfórdio, 252 + 6, 282 - 28);
		this.addElemento(Elementos.Dúbnio, 295 + 6, 282 - 28);
		this.addElemento(Elementos.Seabórgio, 338 + 6, 282 - 28);
		this.addElemento(Elementos.Bóhrio, 381 + 6, 282 - 28);
		this.addElemento(Elementos.Hássio, 424 + 6, 282 - 28);
		this.addElemento(Elementos.Meitnério, 467 + 6, 282 - 28);
		this.addElemento(Elementos.Darmstádio, 510 + 6, 282 - 28);
		this.addElemento(Elementos.Roentgênio, 553 + 6, 282 - 28);
		this.addElemento(Elementos.Copernício, 596 + 6, 282 - 28);

		// Linha 6
		this.addElemento(Elementos.Césio, 123 + 6, 240 - 28);
		this.addElemento(Elementos.Bário, 166 + 6, 240 - 28);
		this.addElemento(Elementos.Háfnio, 252 + 6, 240 - 28);
		this.addElemento(Elementos.Tântalo, 295 + 6, 240 - 28);
		this.addElemento(Elementos.Tungstênio, 338 + 6, 240 - 28);
		this.addElemento(Elementos.Rênio, 381 + 6, 240 - 28);
		this.addElemento(Elementos.Ósmio, 424 + 6, 240 - 28);
		this.addElemento(Elementos.Irídio, 467 + 6, 240 - 28);
		this.addElemento(Elementos.Platina, 510 + 6, 240 - 28);
		this.addElemento(Elementos.Ouro, 553 + 6, 240 - 28);
		this.addElemento(Elementos.Mercúrio, 596 + 6, 240 - 28);
		this.addElemento(Elementos.Tálio, 639 + 6, 240 - 28);
		this.addElemento(Elementos.Chumbo, 682 + 6, 240 - 28);
		this.addElemento(Elementos.Bismuto, 725 + 6, 240 - 28);
		this.addElemento(Elementos.POLÔNIO, 768 + 6, 240 - 28);
		this.addElemento(Elementos.Astato, 811 + 6, 240 - 28);
		this.addElemento(Elementos.Radônio, 854 + 6, 240 - 28);

		// Linha 5
		this.addElemento(Elementos.Rubídio, 123 + 6, 198 - 28);
		this.addElemento(Elementos.Estrôncio, 166 + 6, 198 - 28);
		this.addElemento(Elementos.Ítrio, 209 + 6, 198 - 28);
		this.addElemento(Elementos.Zircônio, 252 + 6, 198 - 28);
		this.addElemento(Elementos.Nióbio, 295 + 6, 198 - 28);
		this.addElemento(Elementos.Molibdênio, 338 + 6, 198 - 28);
		this.addElemento(Elementos.Tecnécio, 381 + 6, 198 - 28);
		this.addElemento(Elementos.Rutênio, 424 + 6, 198 - 28);
		this.addElemento(Elementos.Ródio, 467 + 6, 198 - 28);
		this.addElemento(Elementos.Paládio, 510 + 6, 198 - 28);
		this.addElemento(Elementos.Prata, 553 + 6, 198 - 28);
		this.addElemento(Elementos.Cádmio, 596 + 6, 198 - 28);
		this.addElemento(Elementos.Índio, 639 + 6, 198 - 28);
		this.addElemento(Elementos.Estanho, 682 + 6, 198 - 28);
		this.addElemento(Elementos.Antimônio, 725 + 6, 198 - 28);
		this.addElemento(Elementos.TELÚRIO, 768 + 6, 198 - 28);
		this.addElemento(Elementos.Iodo, 811 + 6, 198 - 28);
		this.addElemento(Elementos.Xenônio, 854 + 6, 198 - 28);

		// Linha 4
		this.addElemento(Elementos.Potássio, 123 + 6, 156 - 28);
		this.addElemento(Elementos.Cálcio, 166 + 6, 156 - 28);
		this.addElemento(Elementos.Escândio, 209 + 6, 156 - 28);
		this.addElemento(Elementos.Titânio, 252 + 6, 156 - 28);
		this.addElemento(Elementos.Vanádio, 295 + 6, 156 - 28);
		this.addElemento(Elementos.Crômio, 338 + 6, 156 - 28);
		this.addElemento(Elementos.Manganês, 381 + 6, 156 - 28);
		this.addElemento(Elementos.Ferro, 424 + 6, 156 - 28);
		this.addElemento(Elementos.Cobalto, 467 + 6, 156 - 28);
		this.addElemento(Elementos.Níquel, 510 + 6, 156 - 28);
		this.addElemento(Elementos.Cobre, 553 + 6, 156 - 28);
		this.addElemento(Elementos.Zinco, 596 + 6, 156 - 28);
		this.addElemento(Elementos.Gálio, 639 + 6, 156 - 28);
		this.addElemento(Elementos.Germânio, 682 + 6, 156 - 28);
		this.addElemento(Elementos.Arsênio, 725 + 6, 156 - 28);
		this.addElemento(Elementos.Selênio, 768 + 6, 156 - 28);
		this.addElemento(Elementos.Bromo, 811 + 6, 156 - 28);
		this.addElemento(Elementos.Criptônio, 854 + 6, 156 - 28);

		// Linha 3
		this.addElemento(Elementos.Sódio, 123 + 6, 114 - 28);
		this.addElemento(Elementos.Magnésio, 166 + 6, 114 - 28);
		this.addElemento(Elementos.Alumínio, 639 + 6, 114 - 28);
		this.addElemento(Elementos.Selício, 682 + 6, 114 - 28);
		this.addElemento(Elementos.Fósforo, 725 + 6, 114 - 28);
		this.addElemento(Elementos.Enxofre, 768 + 6, 114 - 28);
		this.addElemento(Elementos.Cloro, 811 + 6, 114 - 28);
		this.addElemento(Elementos.Argônio, 854 + 6, 114 - 28);

		// Linha 2
		this.addElemento(Elementos.Lítio, 123 + 6, 72 - 28);
		this.addElemento(Elementos.Berílio, 166 + 6, 72 - 28);
		this.addElemento(Elementos.Boro, 639 + 6, 72 - 28);
		this.addElemento(Elementos.Carbono, 682 + 6, 72 - 28);
		this.addElemento(Elementos.Nitrogênio, 725 + 6, 72 - 28);
		this.addElemento(Elementos.Oxigênio, 768 + 6, 72 - 28);
		this.addElemento(Elementos.Flúor, 811 + 6, 72 - 28);
		this.addElemento(Elementos.Néon, 854 + 6, 72 - 28);

		// Linha 1
		this.addElemento(Elementos.Hidrogênio, 123 + 6, 30 - 28);
		this.addElemento(Elementos.Hélio, 854 + 6, 30 - 28);

	}

	private void pedirDica() {
		createWalls();
		createPhysicsBody();

	}

	private void dicaNormal(){
		final Sprite balaoDeDica = new Sprite(930, 0,this.dicaNormalTextureRegion);
		this.cena.registerTouchArea(balaoDeDica);
		this.cena.attachChild(balaoDeDica);

	}

	private void createWalls() {
		final Shape ground = new Rectangle(0, CAMERA_HEIGHT - 2, CAMERA_WIDTH,
				2);
		final Shape roof = new Rectangle(0, 0, CAMERA_WIDTH, 2);
		final Shape left = new Rectangle(0, 0, 2, CAMERA_HEIGHT);
		final Shape right = new Rectangle(CAMERA_WIDTH - 2, 0, 2, CAMERA_HEIGHT);

		//final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 0);
		PhysicsFactory.createBoxBody(this.physicsWorld, ground,
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, roof,
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, left,
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, right,
				BodyType.StaticBody, wallFixtureDef);

		this.cena.attachChild(ground);
		this.cena.attachChild(roof);
		this.cena.attachChild(left);
		this.cena.attachChild(right);
	}

	private void createPhysicsBody() {
		balao = new AnimatedSprite(930, 300,this.balaoDicaTexture);
		final Body body = PhysicsFactory.createBoxBody(this.physicsWorld,balao, BodyType.DynamicBody, FIXTURE_DEF);

		balao.animate(new long[]{430,430,430,430}, 0,3,0, new IAnimationListener() {

			@Override
			public void onAnimationEnd(AnimatedSprite pAnimatedSprite) {
				balao.stopAnimation(3);

			}
		});
		this.cena.registerTouchArea(balao);
		this.cena.attachChild(balao);
		this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(balao,body, true, true));

	}

	private void mostrarDica(final AnimatedSprite face) {
		final PhysicsConnector facePhysicsConnector = this.physicsWorld	.getPhysicsConnectorManager().findPhysicsConnectorByShape(face);

		this.physicsWorld.unregisterPhysicsConnector(facePhysicsConnector);
		this.physicsWorld.destroyBody(facePhysicsConnector.getBody());

		this.cena.unregisterTouchArea(face);
		this.cena.detachChild(face);
		this.pediuDica = false;
		//		this.runOnUiThread(new Runnable() {
		//			public void run() {
		////				AlertDialog.Builder alert = new AlertDialog.Builder(
		////						MyQuimicaActivity.this);
		////				alert.setTitle("Dica");
		////				alert.setMessage(misturaDaVez.getDica());
		//				balaoDialogoQuimico = new Sprite(55, 0, balaoDeInformacoesTextureRegion);
		//				textoDialogoQuimico = new ChangeableText(72, misturaDaVez.getEixoYDica(), fontDialogos, misturaDaVez.getDica());
		//				cena.attachChild(balaoDialogoQuimico);
		//				cena.attachChild(textoDialogoQuimico);
		//				textoPontos.setText(String.valueOf(pontos -= misturaDaVez.getPontos() / 2));
		//				setPontos(pontos -= misturaDaVez.getPontos() / 2);
		//				textoPontos.setPosition(534 - textoPontos.getWidth() / 2, 530);
		////				alert.setPositiveButton("OK", null);
		//				//alert.show();
		//
		//			}
		//		});
		//
		//		System.gc();

		balaoDialogoQuimico = new Sprite(55, 0, balaoDeInformacoesTextureRegion){

			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()){
				case TouchEvent.ACTION_DOWN:
					cena.detachChild(this);
					cena.detachChild(textoDialogoQuimico);
					cena.detachChild(balaoDialogoErroEVazio);
					cena.unregisterTouchArea(this);
					cena.unregisterTouchArea(balaoDialogoErroEVazio);
					break;

				}
				return true;
			}
		};

		textoDialogoQuimico = new ChangeableText(72, misturaDaVez.getEixoYDica(), fontDialogos, misturaDaVez.getDica());
		cena.attachChild(balaoDialogoQuimico);
		cena.registerTouchArea(balaoDialogoQuimico);
		cena.attachChild(textoDialogoQuimico);
		textoPontos.setText("Pontos: " +String.valueOf(j.getPontos()));
		textoPontos.setPosition(534 - textoPontos.getWidth() / 2, 2);
	}	

	private void misturar(String mistura) {
		if (mistura.equals("")) {
			//			this.runOnUiThread(new Runnable() {
			//				public void run() {
			////					AlertDialog.Builder alert = new AlertDialog.Builder(
			////							MyQuimicaActivity.this);
			////					alert.setTitle("Tubo de ensaio Vazio");
			////					alert.setMessage("Coloque algum elemento no tubo de ensaio");
			////					alert.setPositiveButton("OK", null);
			////					//alert.show();
			//					balaoDialogoQuimico = new Sprite(55, 0, balaoDeInformacoesTextureRegion);
			//					textoDialogoQuimico = new ChangeableText(110, 80, fontDialogos, "Tubo de ensaio vazio\nColoque algum elemento");
			//					cena.attachChild(balaoDialogoQuimico);
			//					cena.attachChild(textoDialogoQuimico);
			//
			//
			//				}
			//			});
			balaoDialogoErroEVazio= new Sprite(55, 0, this.balaoDialogoTuboVazioTextureRegion){
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					switch(pSceneTouchEvent.getAction()){
					case TouchEvent.ACTION_DOWN:
						cena.detachChild(this);
						cena.unregisterTouchArea(this);
						cena.detachChild(balaoDialogoQuimico);
						cena.detachChild(textoDialogoQuimico);
						cena.unregisterTouchArea(balaoDialogoQuimico);
					}
					return true;
				}
			};
			//textoDialogoQuimico = new ChangeableText(110, 80, fontDialogos, "Tubo de ensaio vazio\nColoque algum elemento");
			cena.attachChild(balaoDialogoErroEVazio);
			cena.registerTouchArea(balaoDialogoErroEVazio);
			//cena.attachChild(textoDialogoQuimico);
		} else {
			if (!mistura.equals(misturaDaVez.getComposicaoMistura())) {
				//				this.runOnUiThread(new Runnable() {
				//					public void run() {
				//						AlertDialog.Builder alert = new AlertDialog.Builder(
				//								MyQuimicaActivity.this);
				//						alert.setTitle("Resposta Errada");
				//						alert.setMessage("Tente Novamente");
				//						primeiraVez = false;
				//						alert.setPositiveButton("OK", null);
				//						balaoDialogoQuimico = new Sprite(55, 0, balaoDeInformacoesTextureRegion);
				//						textoDialogoQuimico = new ChangeableText(72, 80, fontDialogos, "RESPOSTA ERRADA\n Tente novamente ou\n passe para o pr��ximo desafio");
				//						cena.attachChild(balaoDialogoQuimico);
				//						cena.attachChild(textoDialogoQuimico);
				//						textoPontos.setText(String.valueOf(pontos -= misturaDaVez.getPontos()));
				//						textoPontos.setColor(248,19,13);
				//						setPontos(misturaDaVez.getPontos()/2);
				//						if(pontos < 0){
				//							//textoPontos.setColor(248,19,13);
				//							textoPontos.setPosition(525 - textoPontos.getWidth() / 2, 530);
				//
				//						}
				//						else{
				//							textoPontos.setPosition(534 - textoPontos.getWidth() / 2, 530);
				//							textoPontos.setColor(16,145,239);
				//						}
				//						//alert.show();
				//
				//					}
				//				});
				balaoDialogoErroEVazio = new Sprite(55, 0, balaoDialogoRespostaErradaTextureRegion){
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
						switch(pSceneTouchEvent.getAction()){
						case TouchEvent.ACTION_DOWN:
							cena.detachChild(this);
							cena.unregisterTouchArea(this);
							cena.detachChild(balaoDialogoQuimico);
							cena.detachChild(textoDialogoQuimico);
							cena.unregisterTouchArea(balaoDialogoQuimico);
						}
						return true;
					}
				};
				//	textoDialogoQuimico = new ChangeableText(72, 80, fontDialogos, "RESPOSTA ERRADA\n Tente novamente ou\n passe para o pr��ximo desafio");
				cena.attachChild(balaoDialogoErroEVazio);
				cena.registerTouchArea(balaoDialogoErroEVazio);
				//cena.attachChild(textoDialogoQuimico);
				int pontos = j.getPontos();
				textoPontos.setText("Pontos: " +String.valueOf(pontos -= misturaDaVez.getPontos()/2));
				textoPontos.setColor(248,19,13);
				j.setPontos(-5);
				if(j.getPontos() < 0){
					//textoPontos.setColor(248,19,13);
					textoPontos.setPosition(525 - textoPontos.getWidth() / 2, 2);

				}
				else{
					textoPontos.setPosition(534 - textoPontos.getWidth() / 2, 2);
					textoPontos.setColor(16,145,239);
				}

				//pedirDica();
				if (!pediuDica) {
					this.pediuDica = true;
					pedirDica();
				}


			} else {
				this.runOnUiThread(new Runnable() {
					public void run() {
						/*balaoDialogoQuimico = new Sprite(55, 0, balaoDialogoTextureRegion);
							textoDialogoQuimico = new ChangeableText(72, misturaDaVez.getEixoYInfo(), fontDialogos, misturaDaVez.getInformacao());
							cena.attachChild(balaoDialogoQuimico);
							cena.attachChild(textoDialogoQuimico);*/
						AlertDialog.Builder alert = new AlertDialog.Builder(
								MyQuimicaActivity.this);
						alert.setTitle("Resposta Certa");
						alert.setMessage(misturaDaVez.getInformacao());
						alert.setPositiveButton("Pr�ximo Desafio",
								new OnClickListener() {

							public void onClick(DialogInterface arg0,
									int arg1) {
								contDesafios++;
								zerar();
								if (contDesafios == 5) {
									Intent intent = new Intent(MyQuimicaActivity.this,TelaFinalActivity.class);
									Bundle bundle = new Bundle();
									bundle.putInt("pontos", j.getPontos());
									intent.putExtras(bundle);
									MyQuimicaActivity.this.startActivity(intent);

								} else {
									proximoDesafio();
									contParaTotalDesafios++;
									TextoContDesafios.setText("DESAFIO " + String.valueOf(contParaTotalDesafios)+ "/5");
								}

							}

						});
						int pontos = j.getPontos();
						textoPontos.setText("Pontos: " +String.valueOf(pontos += misturaDaVez.getPontos()));
						j.setPontos(misturaDaVez.getPontos());
						if(j.getPontos()> 0){
							textoPontos.setColor(16,145,239);
						}
						textoPontos.setPosition(534 - textoPontos.getWidth() / 2, 2);
						alert.show();
					}
				});
				gm.adicionarMisturaSelecionada(misturaDaVez);;

			}
		}
	}

	private void proximoDesafio() {
		boolean passou = false;
		while (!passou) {
			misturaDaVez = gm.gerarMistura();
			if ((misturaDaVez != misturaAnterior)
					&& (gm.verificarMisturaRepetida(misturaDaVez) == false)) {
				passou = true;
				misturaAnterior = misturaDaVez;
				this.compostoQuimicoPedido.setText(misturaDaVez.getNome());
				animarMaozinhaDireita();

			}
		}

		this.zerar();
		cena.detachChild(balao);
		cena.unregisterTouchArea(balao);
		cena.unregisterTouchArea(botaoMisturar);
		cena.detachChild(balaoDialogoQuimico);
		cena.detachChild(textoDialogoQuimico);

		if(pediuDica == true){
			cancelarDica((AnimatedSprite) balao);
		}

	}	

	private void cancelarDica(final AnimatedSprite face) {
		final PhysicsConnector facePhysicsConnector = this.physicsWorld	.getPhysicsConnectorManager().findPhysicsConnectorByShape(face);

		this.physicsWorld.unregisterPhysicsConnector(facePhysicsConnector);
		this.physicsWorld.destroyBody(facePhysicsConnector.getBody());

		this.cena.unregisterTouchArea(face);
		this.cena.detachChild(face);
		this.pediuDica = false;
	}



	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {

			final Dialog myDialog = new Dialog(MyQuimicaActivity.this);
			myDialog.setContentView(R.layout.mydialog);
			myDialog.setTitle("            Menu      ");
			myDialog.setCancelable(true);
			Button button = (Button) myDialog.findViewById(R.id.buttonCreditosMenu);
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final Dialog dialogCreditos = new Dialog(MyQuimicaActivity.this);
					dialogCreditos.setContentView(R.layout.custom);
					Button sair = (Button) dialogCreditos.findViewById(R.id.ButtonSairCreditos);

					sair.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							dialogCreditos.dismiss();

						}
					});
					dialogCreditos.show();
				}
			});

			Button buttonSair = (Button) myDialog.findViewById(R.id.buttonSairMenu);
			buttonSair.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					myDialog.dismiss();

				}
			});

			myDialog.show();


			return true;
		}

		else if(pKeyCode==KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN){
			final AlertDialog.Builder alert = new AlertDialog.Builder(MyQuimicaActivity.this);
			alert.setMessage("Deseja sair do jogo?");
			alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					Intent intent1=new Intent(MyQuimicaActivity.this, TelaFinalActivity.class); 
					Bundle bundle = new Bundle();
					int pontos = j.getPontos();
					bundle.putInt("pontos", pontos);
					intent1.putExtras(bundle);
					MyQuimicaActivity.this.startActivity(intent1);
				}
			});

			alert.setNegativeButton("N�O", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {


				}
			});
			alert.show();
			return true;
		}
		else {
			return super.onKeyDown(pKeyCode, pEvent);
		}


	}


	public boolean onMenuItemClicked(final MenuScene pMenuScene,
			final IMenuItem pMenuItem, final float pMenuItemLocalX,
			final float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_MISTURAR:
			this.cena.clearChildScene();
			this.menuScene.reset();
			this.misturar(this.formulaCompostaDoJogador);
			return true;
		case MENU_LIMPAR:
			this.cena.clearChildScene();
			this.menuScene.reset();
			zerar();
			return true;
		case MENU_DICA:
			if (!pediuDica) {
				this.pediuDica = true;
				cena.detachChild(balaoDialogoErroEVazio);
				cena.detachChild(balaoDialogoQuimico);
				cena.detachChild(textoDialogoQuimico);
				pedirDica();
			}
			this.cena.clearChildScene();
			this.menuScene.reset();
			return true;
		case MENU_PROXIMO:
			this.cena.clearChildScene();
			this.menuScene.reset();
			if (contDesafios == 6) {
				this.menuScene.back();
			} else {
				this.proximoDesafio();
			}
			return true;
		default:
			return false;
		}
	}

	//	protected void createMenuScene() {
	//		this.menuScene = new MenuScene(this.camera);
	//
	//		this.sombraDoMenu = new Sprite(0, 0, this.sombra);
	//		this.menuScene.attachChild(this.sombraDoMenu);
	//
	//		final TextMenuItem textoMenuItem = new TextMenuItem(-1, this.fontMenu,
	//				"Menu");
	//		// misturarMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
	//		// GL10.GL_ONE_MINUS_SRC_ALPHA);
	//		this.menuScene.addMenuItem(textoMenuItem);
	//
	//		final SpriteMenuItem misturarMenuItem = new SpriteMenuItem(
	//				MENU_MISTURAR, this.menuMisturarTextureRegion);
	//		misturarMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
	//				GL10.GL_ONE_MINUS_SRC_ALPHA);
	//		this.menuScene.addMenuItem(misturarMenuItem);
	//
	//		final SpriteMenuItem limparMenuItem = new SpriteMenuItem(MENU_LIMPAR,
	//				this.menuLimparTextureRegion);
	//		limparMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
	//				GL10.GL_ONE_MINUS_SRC_ALPHA);
	//		this.menuScene.addMenuItem(limparMenuItem);
	//
	//		final SpriteMenuItem dicaMenuItem = new SpriteMenuItem(MENU_DICA,
	//				this.menuDicaTextureRegion);
	//		dicaMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
	//				GL10.GL_ONE_MINUS_SRC_ALPHA);
	//		this.menuScene.addMenuItem(dicaMenuItem);
	//
	//		final SpriteMenuItem proximoMenuItem = new SpriteMenuItem(MENU_PROXIMO,
	//				this.menuProximoTextureRegion);
	//		proximoMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
	//				GL10.GL_ONE_MINUS_SRC_ALPHA);
	//		this.menuScene.addMenuItem(proximoMenuItem);
	//
	//		this.menuScene.buildAnimations();
	//		this.menuScene.setBackgroundEnabled(false);
	//		this.menuScene.setChildrenVisible(true);
	//		this.menuScene.setOnMenuItemClickListener(this);
	//	}

	private void carregarMisturas() {
		try {
			gm.carregarMisturas();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {

		if (pSceneTouchEvent.isActionDown()) {
			this.mostrarDica((AnimatedSprite) pTouchArea);
			return true;
		}

		else if (pSceneTouchEvent.isActionDown() && (pTouchArea.equals(balaoDialogoQuimico))) {
			cena.detachChild(balaoDialogoQuimico);
			return true;
		}

		else if (pSceneTouchEvent.isActionDown() && (pTouchArea.equals(balaoDialogoErroEVazio))) {
			cena.detachChild(balaoDialogoErroEVazio);
			return true;
		}

		else if(pSceneTouchEvent.isActionDown() && (pSceneTouchEvent.equals(botaoLimpar))){
			this.zerar();
		}


		return false;

	}



	private void zerar() {
		this.cena.detachChild(this.textoElemento);
		this.cena.detachChild(this.textoFormulaQuimica);
		this.cena.detachChild(this.line);
		this.cena.detachChild(balaoDialogoQuimico);
		this.cena.detachChild(balaoDialogoErroEVazio);
		this.cena.detachChild(textoDialogoQuimico);
		this.cena.detachChild(botaoMisturar);
		this.cena.detachChild(botaoLimpar);
		this.cena.detachChild(elementoAux);
		this.cena.detachChild(tuboDeEnsaio);
		this.cena.attachChild(tuboDeEnsaio);
		this.primeiraVez = true;
		this.pediuDicaUmaVez = false;
		this.formulaCompostaDoJogador = "";
		this.formulaJogadorNaTela.setText("");
		this.elementoQuimico.setText("");
		this.contParaSimbolos = 0;
		this.simboloDaVez = "";
		this.simboloAnterior = "";
		this.s = "";
		this.stringAux = "";

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, v.getId(), 0, "Cr�ditos");
		menu.add(0, v.getId(), 0, "Voltar");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}



}
