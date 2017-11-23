import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;
import java.lang.Object;

public class Blend extends Applet{
  // image用のobject
  Image img,img2,new_img;

  int w,h;
  int pix[],pix2[],new_pix[];
  int red[],green[],blue[];
  int red2[],green2[],blue2[];
  int new_red[],new_green[],new_blue[];

  public void init(){
    //イメージを取得
    img = getImage(getCodeBase(),"./plot.png");
    img2 = getImage(getCodeBase(),"./plot2.png");
    //ロードをMediaTrackerを使うことで先に終わらせた
    MediaTracker mt = new MediaTracker(this);
    // リストにイメージの追加
    mt.addImage(img, 0);
    mt.addImage(img2,1);
     try{
       //指定された識別子をもつイメージのロードの開始
       mt.waitForAll();
    } catch (InterruptedException e){}
    // 今回用いる画像は同じサイズなので片方のみのimgの大きさを取得
     w = img.getWidth(this);//イメージの幅を取得
     h = img.getHeight(this);//イメージの高さを取得
    /*新しい配列の作成*/
     pix = new int[w*h]; pix2 = new int[w*h]; new_pix = new int[w*h];
     red = new int[w*h];  green = new int[w*h]; blue = new int[w*h];
     red2 = new int[w*h];  green2 = new int[w*h]; blue2 = new int[w*h];
     new_red = new int[w*h]; new_green = new int[w*h]; new_blue = new int[w*h];
     setPix();
  }

  // 画像のピクセルを取得
  public void setPix(){
    try {
      PixelGrabber pg = new PixelGrabber(img,0,0,w,h,pix,0,w);
      PixelGrabber pg2 = new PixelGrabber(img2,0,0,w,h,pix2,0,w);
      pg.grabPixels();
      pg2.grabPixels();
    }catch (InterruptedException e){}
   /*チャンネル毎に取得*/
    for(int i=0; i<h*w; i++){
     red[i] = (pix[i] >> 16) & 0xff; //赤チャンネル
     green[i] = (pix[i] >> 8) & 0xff; //緑チャンネル
     blue[i] = (pix[i] >> 0) & 0xff; //青チャンネル
     red2[i] = (pix2[i] >> 16) & 0xff; //赤チャンネル
     green2[i] = (pix2[i] >> 8) & 0xff; //緑チャンネル
     blue2[i] = (pix2[i] >> 0) & 0xff; //青チャンネル
    }
    for(int y=0; y<h; y++){
     for(int x=0; x<w; x++){
      new_red[x+y*w] = AlphaBlend(red[y*w+x],red2[y*w+x]);  //赤のガンマ変換
      new_green[x+y*w] = AlphaBlend(green[y*w+x],green2[y*w+x]);  //緑のガンマ変換
      new_blue[x+y*w] = AlphaBlend(blue[y*w+x],blue2[y*w+x]);  //青のガンマ変換
      new_pix[x+y*w] = 0xff000000 & pix[x+y*w] | new_red[x+y*w]<<16 | new_green[x+y*w]<<8 | new_blue[x+y*w];
     }
    }
    MemoryImageSource mimg = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(mimg); //新しいイメージの生成
  }

  public void paint(Graphics g){
    // g.drawImage(img, 0, 0, this); //イメージの描画
    // g.drawImage(img2, w, 0, this); //イメージの描画
    g.drawImage(new_img, 0, 0, this);//変換したイメージの描画
  }

  public int AlphaBlend(int img_color, int img2_color){
     int new_color;
     new_color = (img_color+img2_color)/2;
     return new_color;
   }
}
