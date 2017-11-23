import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;
import java.lang.Object;

public class Reverse extends Applet{
  // image用のobject
  Image img,new_img;

  int w,h;
  int pix[],new_pix[];

  public void init(){
    //イメージを取得
    img = getImage(getCodeBase(),"./sample3.png");
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
     setPix();
  }

  // 画像のピクセルを取得
  public void setPix(){
    try {
      PixelGrabber pg = new PixelGrabber(img,0,0,w,h,pix,0,w);
      pg.grabPixels();
    }catch (InterruptedException e){}
    for(int y=0; y<h; y++){
     for(int x=0; x<w; x++){
       new_pix[(y+1)*w - (x+1)] = pix[y*w+x];
     }
    }
    MemoryImageSource mimg = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(mimg); //新しいイメージの生成
  }

  public void paint(Graphics g){
    g.drawImage(img, 0, 0, this); //イメージの描画
    g.drawImage(new_img, w+1, 0, this);//変換したイメージの描画
  }
}
