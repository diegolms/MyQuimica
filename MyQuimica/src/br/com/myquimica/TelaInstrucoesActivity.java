package br.com.myquimica;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
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
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseSineInOut;

import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;

public class TelaInstrucoesActivity extends BaseGameActivity{

	private static final int CAMERA_WIDTH = 1024;
	private static final int CAMERA_HEIGHT = 600;

	protected Camera mCamera;

	protected Scene cena;


	private BitmapTextureAtlas cenarioTexture,cenarioBancadaTexture,jogar,quimicoTexture,tuboTexture,tuboVerdeTexture;
	protected TextureRegion cenarioTextureRegion,jogarTextureRegion,cenarioBancadaTextureRegion,tuboVerdeTextureRegion;

	protected TiledTextureRegion quimicoTextureRegion,tuboDeEnsaioTextureRegion;

	private Sprite maozinha, maozinhaDireita;
	private Sprite elementoHid;
	private Sprite balaoDialogoQuimico;
	private AnimatedSprite quimico,tuboDeEnsaio;

	private ChangeableText textoDialogoQuimico;
	private BitmapTextureAtlas fonteDialogoTexture;

	private BitmapTextureAtlas mao,elemento, balaoDialogo, maoDireita;
	private TextureRegion maoTextureRegion, elementoTextureRegion, balaoDialogoTextureRegion, maoDireitaTextureRegion;

	private Font fontDialogos;


	private final long[] DURACAO_ANIMACAO = new long[] { 100, 100, 100};
	private BitmapTextureAtlas fonteTexture;
	private BitmapTextureAtlas fonteDesafioTexture;
	private Font fontContDesafios;


	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera);
		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		return new Engine(engineOptions);
	}

	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.fonteDesafioTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fontContDesafios = FontFactory.createFromAsset(this.fonteDesafioTexture,this, "font/Archive.otf", 35, true, Color.rgb(0,130,238));

		this.cenarioTexture = new BitmapTextureAtlas(1024, 1024,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.cenarioTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.cenarioTexture, this, "telajogo_instrucoes_FUNDO.png", 0, 0);

		this.tuboVerdeTexture = new BitmapTextureAtlas(256, 256,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.tuboVerdeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.tuboVerdeTexture, this, "telajogo_cenario_INSTRUMENTOS_03.png", 0,0);

		this.jogar= new BitmapTextureAtlas(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.jogarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.jogar, this, "telajogo_instrucoes_PLAY.png", 0, 0);


		this.mEngine.getTextureManager().loadTextures(this.cenarioTexture,this.jogar,this.tuboVerdeTexture, this.fonteDesafioTexture);
		this.mEngine.getFontManager().loadFonts(this.fontContDesafios);



	}

	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.cena = new Scene();
		this.cena.setBackgroundEnabled(false);

		montarCena();

		this.comecarJogo();	




		return this.cena;	}

	private void montarCena() {
		this.cena.attachChild(new Sprite(0, 0, this.cenarioTextureRegion));
		this.cena.attachChild(new ChangeableText(10, 50, this.fontContDesafios, "1 - Observe o desafio pedido\n\n" +
				"2 - Coloque no tubo de ensaio os elementos que\nformam a nomenclatura pedida\n\n3- Clique no botão misturar\n\n" +
				"4 - Caso erre a mistura, remova os elementos do \ntubo de ensaio através do botão limpar" +
				" e tente \nnovamente ou passe para o próximo desafio"));



	}

	public void onLoadComplete() {


	}


	private void comecarJogo(){

		final Sprite botaoJogar = new Sprite(390, 420, this.jogarTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					Intent intent1=new Intent(TelaInstrucoesActivity.this, MyQuimicaActivity.class); 
					intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent1);
					break;
				}
				return true;


			}
		};


		this.cena.attachChild(botaoJogar);
		this.cena.registerTouchArea(botaoJogar);



	}
}