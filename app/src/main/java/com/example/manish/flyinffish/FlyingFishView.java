package com.example.manish.flyinffish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View {

    private Bitmap fish[]=new Bitmap[2];
    private  int fishX=10;
    private  int fishY;
    private int fishSpeed;
    private int canvasWidth,canvasHight;

    //Yellow Ball
    private int yellowX,yellowY,yellowSpeed=16;
    private Paint yellowPaint=new Paint();

    //Green Ball
    private int greenX,greenY,greenSpeed=20;
    private Paint greenPaint=new Paint();

    //Red Ball
    private int redX,redY,redSpeed=25;
    private Paint redPaint=new Paint();


    private int score, lifeCounterOfFish;

    private boolean touch=false;

    private Bitmap backGroundImage;

    private Paint scorePaint=new Paint();

    private Bitmap life[]=new Bitmap[2];

    public FlyingFishView(Context context) {
        super(context);
        fish[0]=BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]=BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backGroundImage=BitmapFactory.decodeResource(getResources(),R.drawable.background);

        //Yellow Ball
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        //Green Ball
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

            //Red Ball
        redPaint.setColor(Color.RED);
       redPaint.setAntiAlias(false);


        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishY=550;
        score=0;
        lifeCounterOfFish=3;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth=canvas.getWidth();
        canvasHight=canvas.getHeight();


        canvas.drawBitmap(backGroundImage,0,0,null);
       // canvas.drawBitmap(fish,0,0,null);

        int minFishY=fish[0].getHeight();
        int maxFishY=canvasHight-fish[0].getHeight()*3;
        fishY=fishY+fishSpeed;
        if(fishY<minFishY)
        {
            fishY=minFishY;
        }if (fishY>maxFishY){
            fishY=maxFishY;
        }
        fishSpeed=fishSpeed+2;
        if(touch){
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }else {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }



        //YellowBall Code...
        yellowX=yellowX-yellowSpeed;

        if(hitBallChecker(yellowX,yellowY)){
            score=score+10;
            yellowX= -100;
        }

        if(yellowX<0){
            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;

        }
        canvas.drawCircle(yellowX,yellowY,25,yellowPaint);


        //Green Ball Code...
        greenX=greenX-greenSpeed;

        if(hitBallChecker(greenX,greenY)){
            score = score + 25;
            greenX = -100;
        }

        if(greenX < 0){
            greenX=canvasWidth+21;
            greenY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;

        }
        canvas.drawCircle(greenX,greenY,35,greenPaint);

        //Red Ball Code
        redX=redX-redSpeed;

        if(hitBallChecker(redX,redY)){

            redX = -100;
            lifeCounterOfFish--;

            if(lifeCounterOfFish==0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameOverIntent=new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score",score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if(redX < 0){
           redX=canvasWidth+21;
            redY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;

        }
        canvas.drawCircle(redX,redY,30,redPaint);

        canvas.drawText("Score: "+score,20,60,scorePaint);


        for(int i=0;i<3;i++){
            int x=(int)(580+life[0].getWidth()*1.5*i);
            int y=30;
            if(i<lifeCounterOfFish)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }else
            {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }




//        canvas.drawBitmap(life[0],580,10,null);
//        canvas.drawBitmap(life[0],680,10,null);
//        canvas.drawBitmap(life[0],780,10,null);
    }

    public boolean hitBallChecker(int x,int y){
      if(fishX<x && x<(fishX+fish[0].getWidth())&& fishY<y && y<(fishY+fish[0].getHeight()))
      {
        return true;
      }
      return false;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch=true;
            fishSpeed=-22;
        }
        return true;
    }
}
