package com.tolgaalperkus.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture backgroundTexture;
	Texture birdTexture;
	Texture bee1Texture,bee2Texture,bee3Texture;
	int widthMax, heightMax;
	Bird bird;
	int gameState = 0;
	float velocity = 0f;
	float gravity = 0.1f;
	Random random;
	Circle birdCircle;
	int numberOfEnemies = 4;
	float[] xEnemy = new float[numberOfEnemies];
	float distance = 0;
	float velocityEnemy = 2f;
	float[] enemyOffset = new float[numberOfEnemies];
	float[] enemyOffset2 = new float[numberOfEnemies];
	float[] enemyOffset3 = new float[numberOfEnemies];
	Circle[] enemyCircles;
	Circle[] enemyCircles2;
	Circle[] enemyCircles3;
	ShapeRenderer shapeRenderer;
	int score = 0;
	int scoredEnemy = 0;
	BitmapFont font,font2;

	@Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);
		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(6);

		batch = new SpriteBatch();
		backgroundTexture = new Texture("background.png");
		birdTexture = new Texture("bird1.png");
		bee1Texture = new Texture("sprite1.png");
		bee2Texture = new Texture("sprite1.png");
		bee3Texture = new Texture("sprite1.png");

		shapeRenderer = new ShapeRenderer();

		heightMax = Gdx.graphics.getHeight();
		widthMax = Gdx.graphics.getWidth();
		bird = new Bird(heightMax,widthMax);
		birdCircle = new Circle();
		enemyCircles = new Circle[numberOfEnemies];
		enemyCircles2 = new Circle[numberOfEnemies];
		enemyCircles3 = new Circle[numberOfEnemies];
		distance = widthMax/2;
		random = new Random();

		for(int i = 0; i<numberOfEnemies; i++){
			enemyOffset[i] = (random.nextFloat()-0.5f)*heightMax -200;
			enemyOffset2[i] = (random.nextFloat()-0.5f)*heightMax -200;
			enemyOffset3[i] = (random.nextFloat()-0.5f)*heightMax -200;
			xEnemy[i] = widthMax-bee1Texture.getWidth()*0.5f + i*distance;
			enemyCircles[i] = new Circle();
			enemyCircles2[i] = new Circle();
			enemyCircles3[i] = new Circle();
		}
	}

	@Override
	public void render ()
	{
		batch.begin();
		batch.draw(backgroundTexture,0,0, widthMax, heightMax);
		if(gameState == 1)
		{
			if(xEnemy[scoredEnemy]<widthMax*0.5f-bird.height()*0.5f){
				score++;
				if(scoredEnemy<numberOfEnemies-1){
					scoredEnemy++;
				}else{
					scoredEnemy = 0;
				}
			}
			if(Gdx.input.justTouched())
			{
				velocity = -bird.flapHeight();
			}

			for(int i=0;i<numberOfEnemies;i++){

				if(xEnemy[i] < -bee1Texture.getWidth()){
					xEnemy[i] = xEnemy[i] + numberOfEnemies * distance;
					enemyOffset[i] = (random.nextFloat()-0.5f)*heightMax -200;
					enemyOffset2[i] = (random.nextFloat()-0.5f)*heightMax -200;
					enemyOffset3[i] = (random.nextFloat()-0.5f)*heightMax -200;

				}else{
					xEnemy[i] = xEnemy[i] - velocityEnemy;
				}

				batch.draw(bee1Texture,xEnemy[i],heightMax*0.5f + enemyOffset[i],bird.width(),bird.height());
				batch.draw(bee2Texture,xEnemy[i],heightMax*0.5f + enemyOffset2[i],bird.width(),bird.height());
				batch.draw(bee3Texture,xEnemy[i],heightMax*0.5f + enemyOffset3[i],bird.width(),bird.height());

				enemyCircles[i] = new Circle(xEnemy[i]+(bird.width()*0.5f),(heightMax*0.5f + enemyOffset[i]+(bird.height()*0.5f)),bird.width()*0.5f);
				enemyCircles2[i] = new Circle(xEnemy[i]+(bird.width()*0.5f),(heightMax*0.5f+ enemyOffset2[i]+(bird.height()*0.5f)),bird.width()*0.5f);
				enemyCircles3[i] = new Circle(xEnemy[i]+(bird.width()*0.5f),(heightMax*0.5f+ enemyOffset3[i]+(bird.height()*0.5f)),bird.width()*0.5f);

			}
			if(bird.getyPlace()+bird.height()*0.5f > 0 && bird.getyPlace() < heightMax-bird.width()*0.5f)
			{
				velocity=velocity+gravity;
				bird.fall(velocity);
			}else{
				gameState = 2;
			}
		}
		else if(gameState == 0){
			if(Gdx.input.justTouched())
			{
				gameState = 1;
			}
		}
		else if(gameState==2){
			font2.draw(batch,"Game Over Tap To Play Again",100,heightMax/2);
			if(Gdx.input.justTouched()){
				gameState = 1;
				bird.setDefault();
				for(int i = 0; i<numberOfEnemies; i++){

					enemyOffset[i] = (random.nextFloat()-0.5f)*heightMax -200;
					enemyOffset2[i] = (random.nextFloat()-0.5f)*heightMax -200;
					enemyOffset3[i] = (random.nextFloat()-0.5f)*heightMax -200;
					xEnemy[i] = widthMax-bee1Texture.getWidth()*0.5f + i*distance;

					enemyCircles[i] = new Circle();
					enemyCircles2[i] = new Circle();
					enemyCircles3[i] = new Circle();

				}
				velocity = 0;
				scoredEnemy = 0;
				score = 0;

			}
		}

		batch.draw(birdTexture,bird.getxPlace(),bird.getyPlace(),bird.width(),bird.height());
		font.draw(batch,String.valueOf(score),100,200);
		batch.end();
		birdCircle.set(bird.getxPlace()+bird.width()*0.5f,bird.getyPlace()+bird.height()*0.5f,bird.width()*0.5f);
		/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);*/

		for(int i = 0; i<numberOfEnemies; i++){
			/*shapeRenderer.circle(xEnemy[i]+(bird.width()*0.5f),(heightMax*0.5f + enemyOffset[i]+(bird.height()*0.5f)),bird.width()*0.5f);
			shapeRenderer.circle(xEnemy[i]+(bird.width()*0.5f),(heightMax*0.5f + enemyOffset2[i]+(bird.height()*0.5f)),bird.width()*0.5f);
			shapeRenderer.circle(xEnemy[i]+(bird.width()*0.5f),(heightMax*0.5f + enemyOffset3[i]+(bird.height()*0.5f)),bird.width()*0.5f);*/
			if(Intersector.overlaps(birdCircle,enemyCircles[i]) || Intersector.overlaps(birdCircle,enemyCircles2[i]) || Intersector.overlaps(birdCircle,enemyCircles3[i])){
				gameState = 2;
			}
		}
		//shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
	}
}
