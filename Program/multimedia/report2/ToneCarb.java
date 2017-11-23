import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;
import java.lang.Object;

public class ToneCarb extends Applet{
  // image用のobject
  Image img,new_img;
  double gamma = 2;

  int w,h;
  int pix[],new_pix[];
  int red[],green[],blue[];
  int new_red[],new_green[],new_blue[];

  public void init(){
    //イメージを取得
    img = getImage(getCodeBase(),"./sample.jpg");
    //ロードをMediaTrackerを使うことで先に終わらせた
    MediaTracker mt = new MediaTracker(this);
    // リストにイメージの追加
    mt.addImage(img, 0);
    try{
       //指定された識別子をもつイメージのロードの開始
       mt.waitForID(0);
    } catch (InterruptedException e){}
     w = img.getWidth(this);//イメージの幅を取得
     h = img.getHeight(this);//イメージの高さを取得
    /*新しい配列の作成*/
     pix = new int[w*h]; new_pix = new int[w*h];
     red = new int[w*h];  green = new int[w*h]; blue = new int[w*h];
     new_red = new int[w*h]; new_green = new int[w*h]; new_blue = new int[w*h];
     setPix();
  }

  // 画像のピクセルを取得
  public void setPix(){
    try {
      PixelGrabber pg = new PixelGrabber(img,0,0,w,h,pix,0,w);
      pg.grabPixels();
    }catch (InterruptedException e){}
   /*チャンネル毎に取得*/
    for(int i=0; i<h*w; i++){
     red[i] = (pix[i] >> 16) & 0xff; //赤チャンネル
     green[i] = (pix[i] >> 8) & 0xff; //緑チャンネル
     blue[i] = (pix[i] >> 0) & 0xff; //青チャンネル
    }
    for(int y=0; y<h; y++){
     for(int x=0; x<w; x++){
      new_red[x+y*w] = GammaConvert(red[y*w+x],gamma);  //赤のガンマ変換
      new_green[x+y*w] = GammaConvert(green[y*w+x],gamma);  //緑のガンマ変換
      new_blue[x+y*w] = GammaConvert(blue[y*w+x],gamma);  //青のガンマ変換
      new_pix[x+y*w] = 0xff000000 & pix[x+y*w] | new_red[x+y*w]<<16 | new_green[x+y*w]<<8 | new_blue[x+y*w];
     }
    }
    MemoryImageSource mimg = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(mimg); //新しいイメージの生成
  }

  public void paint(Graphics g){
    g.drawImage(img, 0, 0, this); //イメージの描画
    g.drawImage(new_img, w+1, 0, this);//変換したイメージの描画
  }

  public int GammaConvert(int color,double gamma){
     double newcolor = (double)color;
     // ガンマ変換の数式
     newcolor = 255*(Math.pow((newcolor/255),(1/gamma)));
     color = (int)newcolor;
     return color;
   }
}
