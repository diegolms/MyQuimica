package br.com.myquimica;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.animator.IMenuAnimator;
import org.anddev.andengine.entity.scene.menu.animator.SlideMenuAnimator;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import br.com.myquimica.core.FController;
import br.com.myquimica.models.Jogador;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class TelaMenuActivity extends BaseGameActivity implements IOnMenuItemClickListener{

	private static final int CAMERA_WIDTH = 1024;
	private static final int CAMERA_HEIGHT = 600;
	
	protected static final int MENU_NOVOJOGO = 0;
	protected static final int MENU_INSTRUCOES = MENU_NOVOJOGO + 1;
	protected static final int MENU_QUIT = MENU_INSTRUCOES + 1;

	protected Camera mCamera;	

	protected Scene mMainScene;

	private BitmapTextureAtlas tituloTexture;
	private TextureRegion tituloTextureRegion;

	protected MenuScene mMenuScene;
	private BitmapTextureAtlas cenarioTexture;
	private TextureRegion cenarioTextureRegion;
	private BitmapTextureAtlas mMenuTexture;
	protected TextureRegion mNovoJogoTextureRegion;
	protected TextureRegion mComoJogarTextureRegion;
	protected TextureRegion mSairTextureRegion;
	protected TextureRegion mMenuAlphaAux;

	
	@Override
	protected void onCreate(Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		this.populateDb();
	}



	private void populateDb() {
		List<Jogador> aux = FController.getInstance().buscarListaJogadores(getApplicationContext());
		if((aux==null) || (aux.isEmpty())){
			FController.getInstance().insertMandado(new Jogador("Jogador" , 0), getApplicationContext());
			FController.getInstance().insertMandado(new Jogador("Jogador" , 0), getApplicationContext());
			FController.getInstance().insertMandado(new Jogador("Jogador" , 0), getApplicationContext());
			FController.getInstance().insertMandado(new Jogador("Jogador" , 0), getApplicationContext());
		}
		
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
		this.tituloTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.tituloTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.tituloTexture, this, "gfx/MyQuimica.png", 0,0);

		this.cenarioTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.DEFAULT);
		this.cenarioTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.cenarioTexture, this, "gfx/tela_inicial_FINAL.png", 0, 0);
		

		this.mMenuTexture= new BitmapTextureAtlas(2048,2048, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mNovoJogoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "gfx/NOVOJOGO.png", 0, 0);
		this.mMenuAlphaAux = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "gfx/menuAlpha.png", 0, 50);
		this.mComoJogarTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "gfx/COMOJOGAR.png", 0,100 );
		this.mSairTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, this, "gfx/SAIR.png",0, 200);


		this.mEngine.getTextureManager().loadTextures(this.cenarioTexture,this.tituloTexture, this.mMenuTexture);


	}


	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.mMenuScene = this.createMenuScene();

		this.mMainScene = new Scene(0);


		this.mMainScene.setBackgroundEnabled(false);
		this.mMainScene.attachChild(new Sprite(0, 0, this.cenarioTextureRegion));
		this.mMainScene.attachChild(new Sprite(163, 22, this.tituloTextureRegion));

		this.mMainScene.setChildScene(this.mMenuScene, false, true, true);
	
			
		return this.mMainScene;
		
	}

	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

		
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,	float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case MENU_NOVOJOGO:
			Intent intent1=new Intent(TelaMenuActivity.this, MyQuimicaActivity.class); 
			intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);	
			return true;
		case MENU_INSTRUCOES:
			Intent intent4=new Intent(TelaMenuActivity.this, TelaInstrucoesActivity.class); 
			startActivity(intent4);
			return true;
		case MENU_QUIT:
			this.finish();
			return true;
		default:
			return false;
		}
	}

	protected MenuScene createMenuScene() {
		final MenuScene menuScene = new MenuScene(this.mCamera);
		
		final SpriteMenuItem novoJogoMenuItem = new SpriteMenuItem(MENU_NOVOJOGO, this.mNovoJogoTextureRegion);
		novoJogoMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(novoJogoMenuItem);
		
		final SpriteMenuItem novoJogoAlpha = new SpriteMenuItem(4, this.mMenuAlphaAux);
		menuScene.addMenuItem(novoJogoAlpha);

		final SpriteMenuItem comoJogarMenuItem = new SpriteMenuItem(MENU_INSTRUCOES, this.mComoJogarTextureRegion);
		comoJogarMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(comoJogarMenuItem);		

		final SpriteMenuItem comoJogarAlpha = new SpriteMenuItem(5,  this.mMenuAlphaAux);
		menuScene.addMenuItem(comoJogarAlpha);

		final SpriteMenuItem sairMenuItem = new SpriteMenuItem(MENU_QUIT, this.mSairTextureRegion);		
		sairMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(sairMenuItem);
		
		menuScene.setPosition(-10, -50);

		menuScene.buildAnimations();
				
		menuScene.setBackgroundEnabled(false);

		menuScene.setOnMenuItemClickListener(this);


		return menuScene;
	}

	

}
