package com.xdpm.angrybirds.gamelevel.object.barrier;

import org.andengine.entity.Entity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.texturepack.TexturePackTextureRegionLibrary;
import org.andengine.util.debug.Debug;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.xdpm.angrybirds.common.BarrierConstants;
import com.xdpm.angrybirds.gamelevel.GameLevel;
import com.xdpm.angrybirds.manager.GameLevelManager;
import com.xdpm.angrybirds.manager.ResourceManager;
import com.xdpm.angrybirds.scene.MainGameScene;

/**
 * 
 * @author Nguyen Anh Tuan
 *
 */
public class IceBarrierObject extends BaseBarrierObject{

	public IceBarrierObject(float pX, float pY, float pTextureRotation, char pBarrierClass) {
		super(pX, pY, pTextureRotation);
		
		ITiledTextureRegion tiledTextureRegion = null;
		TexturePackTextureRegionLibrary barrierTexturePackLibrary = ResourceManager.getInstance().getIceBarrierSpriteSheetTexturePackTextureRegionLibrary();
		switch (pBarrierClass) {
		case BarrierConstants.CLASS_STRAIGHT_01:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.STRAIGHT_ICE_01_ID, 4, 1);
            Debug.e("ICE CLASS_STRAIGHT_01: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_STRAIGHT_02:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.STRAIGHT_ICE_02_ID, 4, 1);
            Debug.e("ICE CLASS_STRAIGHT_02: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_STRAIGHT_03:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.STRAIGHT_ICE_03_ID, 4, 1);
            Debug.e("ICE CLASS_STRAIGHT_03: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_STRAIGHT_04:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.STRAIGHT_ICE_04_ID, 4, 1);
            Debug.e("ICE CLASS_STRAIGHT_04: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_SQUARE_01:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.SQUARE_ICE_01_ID, 4, 1);
            Debug.e("ICE CLASS_SQUARE_01: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_SQUARE_02:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.SQUARE_ICE_02_ID, 4, 1);
            Debug.e("ICE CLASS_SQUARE_02: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_SQUARE_HOLLOW:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.SQUARE_ICE_HOLLOW_ID, 4, 1);
            Debug.e("ICE CLASS_SQUARE_HOLLOW: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_TRIANGLE_FILLED:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.TRIANGLE_ICE_FILLED_ID, 4, 1);
            Debug.e("ICE CLASS_TRIANGLE_FILLED: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_TRIANGLE_HOLLOW:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.TRIANGLE_ICE_HOLLOW_ID, 4, 1);
            Debug.e("ICE CLASS_TRIANGLE_HOLLOW: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_CIRCLE_BIG:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.CIRCLE_ICE_BIG_ID, 4, 1);
            Debug.e("ICE CLASS_CIRCLE_BIG: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;
		case BarrierConstants.CLASS_CIRCLE_SMALL:
			tiledTextureRegion = barrierTexturePackLibrary.get(BarrierConstants.CIRCLE_ICE_SMALL_ID, 4, 1);
            Debug.e("ICE CLASS_CIRCLE_SMALL: w = " + tiledTextureRegion.getWidth() + ", h = " + tiledTextureRegion.getHeight());
			break;

		default:
			break;
		}
		
		this.setBarrierData(tiledTextureRegion);
	}

	@Override
	public void createBody(BodyType pBodyType, boolean pUpdatePosition,
			boolean pUpdateRotation) {
		this.mBody = PhysicsFactory.createBoxBody(GameLevelManager.getInstance().getPhysicsWorld(), this.mEntity, pBodyType, BarrierConstants.ICE_BARRIER_FIXTURE_DEF);
		this.mPhysicsConnector = new PhysicsConnector(this.mEntity, this.mBody, pUpdatePosition, pUpdateRotation);
		this.mBody.setUserData(this);
	}

	@Override
	public void onDestroy() {
		IceBarrierSmash smash1 = new IceBarrierSmash(this.mEntity.getX() + this.mEntity.getWidth() * 0.5f, this.mEntity.getY() + this.mEntity.getHeight() * 0.5f);
		IceBarrierSmash smash2 = new IceBarrierSmash(this.mEntity.getX() + this.mEntity.getWidth() * 0.5f, this.mEntity.getY() + this.mEntity.getHeight() * 0.5f);
		IceBarrierSmash smash3 = new IceBarrierSmash(this.mEntity.getX() + this.mEntity.getWidth() * 0.5f, this.mEntity.getY() + this.mEntity.getHeight() * 0.5f);
		IceBarrierSmash smash4 = new IceBarrierSmash(this.mEntity.getX() + this.mEntity.getWidth() * 0.5f, this.mEntity.getY() + this.mEntity.getHeight() * 0.5f);
		
		PhysicsWorld physicsWorld = GameLevelManager.getInstance().getPhysicsWorld();
		Entity frontLayer = GameLevelManager.getInstance().getCurrentGameLevel().getFrontLayer();
		
/*		physicsWorld.registerPhysicsConnector(smash1.getPhysicsConnector());
		physicsWorld.registerPhysicsConnector(smash2.getPhysicsConnector());
		physicsWorld.registerPhysicsConnector(smash3.getPhysicsConnector());
		physicsWorld.registerPhysicsConnector(smash4.getPhysicsConnector());*/
		
		frontLayer.attachChild(smash1.getEntity());
		frontLayer.attachChild(smash2.getEntity());
		frontLayer.attachChild(smash3.getEntity());
		frontLayer.attachChild(smash4.getEntity());
	}
}
