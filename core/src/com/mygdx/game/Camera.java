package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

public class Camera {

	private final float WORLD_TO_RENDER = 96f;
    private final float RENDER_TO_WORLD = 1/96f;
	
    private OrthographicCamera worldCamera, lightCamera;
    private Matrix4 cameraBox2D;
    
	public Camera(float w, float h) {
		worldCamera = new OrthographicCamera(w, h);
		lightCamera = new OrthographicCamera(w*RENDER_TO_WORLD, h*RENDER_TO_WORLD);
		
		worldCamera.update();
		lightCamera.update();
	}
	
	//Character (x,y) and width & height
	public void update(float posX, float posY, float width, float height) {
		
		worldCamera.position.set((posX*WORLD_TO_RENDER)+
								(width/2), (posY*WORLD_TO_RENDER)+(height/2), 0);
		
		lightCamera.position.set(posX, posY, 0);
		
		worldCamera.update();
		lightCamera.update();
		
		cameraBox2D = worldCamera.combined.cpy();
        cameraBox2D.scl(WORLD_TO_RENDER);
	}
	
	public OrthographicCamera getWorld() {
		return worldCamera;
	}
	
	public OrthographicCamera getLight() {
		return lightCamera;
	}
	
	public Matrix4 getDebug() {
		return cameraBox2D;
	}
	
	public float WORLD_TO_RENDER() {
		return WORLD_TO_RENDER;
	}
	
	public float RENDER_TO_WORLD() {
		return RENDER_TO_WORLD;
	}
	
}
