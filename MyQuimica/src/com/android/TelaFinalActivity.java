package com.android;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.entity.sprite.Sprite;
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
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

public class TelaFinalActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 1024;
	private static final int CAMERA_HEIGHT = 600;

	protected Camera mCamera;

	protected Scene mMainScene;
	private BitmapTextureAtlas fonteTexture;

	private Font fontScore;
	private BitmapTextureAtlas cenarioTexture;
	private TextureRegion cenarioTextureRegion, salvarTextureRegion;
	private BitmapTextureAtlas fontePontosTexture, salvar;
	private Font fontPontos;
	private BitmapTextureAtlas fonteMensagemTexture;
	private Font fontMensagem;

	protected static final int MENU_NOVOJOGO = 0;
	protected static final int MENU_INSTRUCOES = MENU_NOVOJOGO + 1;

	private int pontos;
	private BitmapTextureAtlas botaoJogarNovamenteTexture;
	private BitmapTextureAtlas botaoSairTexture;
	private TextureRegion botaoJogarNovamenteTextureRegion;
	private TextureRegion mSairTextureRegion;


	@Override
	protected void onCreate(Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		pontos = extras.getInt("pontos");

	}

	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera);
		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		return new Engine(engineOptions);

	}

	public void onLoadResources() {

		this.salvar= new BitmapTextureAtlas(512,64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.salvarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.salvar, this, "gfx/SALVAR.png", 0, 0);

		this.fonteTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fontScore = FontFactory.createFromAsset(this.fonteTexture, this, "font/Archive.otf", 100, true, Color.BLUE);

		this.fontePontosTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fontPontos = FontFactory.createFromAsset(this.fontePontosTexture , this, "font/Archive.otf", 60, true, Color.BLACK);

		this.fonteMensagemTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.fontMensagem = FontFactory.createFromAsset(this.fonteMensagemTexture , this, "font/Archive.otf", 60, true, Color.RED);

		this.cenarioTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.DEFAULT);
		this.cenarioTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.cenarioTexture, this, "gfx/telaFInal.png", 0, 0);


		this.botaoJogarNovamenteTexture = new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.botaoSairTexture= new BitmapTextureAtlas(512,512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.botaoJogarNovamenteTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.botaoJogarNovamenteTexture, this, "gfx/Controls_Menos.png", 0,0);
		this.mSairTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.botaoSairTexture, this, "gfx/Controls_MenosDois.png",0, 0);


		this.mEngine.getTextureManager().loadTextures(this.cenarioTexture,this.fonteTexture,
				this.fontePontosTexture, this.fonteMensagemTexture,this.botaoJogarNovamenteTexture,this.botaoSairTexture);
		this.mEngine.getFontManager().loadFonts(this.fontScore,this.fontPontos, this.fontMensagem);
	}

	public Scene onLoadScene() {


		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.mMainScene = new Scene();

		this.mMainScene.setBackgroundEnabled(false);
		this.mMainScene.attachChild(new Sprite(0, 0, this.cenarioTextureRegion));


		Text textoFormulaQuimica = new Text(250, 10,this.fontScore, "PARABÉNS");
		Text textoPontos = new Text(210, 250, this.fontPontos,"Você fez: " +pontos+ " Pontos");
		Text textoMensagem= new Text(130, 430, this.fontMensagem,"O estudo é a base para o");
		Text textoMensagem2= new Text(390, 480, this.fontMensagem,"SUCESSO");

		this.mMainScene.attachChild(textoFormulaQuimica);
		this.mMainScene.attachChild(textoPontos);
		this.mMainScene.attachChild(textoMensagem);
		this.mMainScene.attachChild(textoMensagem2);

		this.salvar();
		
		this.criarBotoes();	

		return this.mMainScene;	}

	public void onLoadComplete() {


	}

	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
		if (pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {

			return true;
		}

		else if(pKeyCode==KeyEvent.KEYCODE_BACK && pEvent.getAction() == KeyEvent.ACTION_DOWN){
//			Intent intent1 = new Intent(TelaFinalActivity.this, TelaMenuActivity.class); 
//			intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent1);
			return true;
		}
		else {
			return super.onKeyDown(pKeyCode, pEvent);
		}


	}


	private void getNomeUsuario(){
		final AlertDialog.Builder alert = new AlertDialog.Builder(TelaFinalActivity.this);
		final EditText in = new EditText(this);
		alert.setMessage("Digite Seu Nome");
		alert.setView(in);
		alert.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				Editable nome = in.getText();
				Log.d("LOGS", nome.toString());
			}
		});

		alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();

	}


	private void salvar(){
		final Sprite salvar = new Sprite(300,300,this.salvarTextureRegion){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()){
					getNomeUsuario();
				}
				return true;
			}
		};




	}

	private void criarBotoes(){

		final Sprite botaoJogarNovamente = new Sprite(20, 530, this.botaoJogarNovamenteTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					Intent intent1=new Intent(TelaFinalActivity.this, MyQuimicaActivity.class); 
					intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent1);
					break;
				}
				return true;


			}
		};


		this.mMainScene.attachChild(botaoJogarNovamente);
		this.mMainScene.registerTouchArea(botaoJogarNovamente);

		final Sprite botaoSair = new Sprite(860, 530, this.mSairTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				switch(pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					Intent intent1 = new Intent(TelaFinalActivity.this, TelaMenuActivity.class); 
					intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent1);
				}
				return true;


			}
		};


		this.mMainScene.attachChild(botaoSair);
		this.mMainScene.registerTouchArea(botaoSair);
	}


}
