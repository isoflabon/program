import java.applet. *;
import java.awt. *;
import java.awt.image. *;

public class auto extends Applet {

	Image img;

	int w = 300;
	int h = 300;

	int pix[] = new int[w*h];
	IndexColorModel cm;

	public void init() {
		byte r[] = {(byte) 0x99,(byte) 0x00};
		byte g[] = {(byte) 0x99,(byte) 0xff};
		byte b[] = {(byte) 0x99,(byte) 0xff};

		cm = new IndexColorModel(8,2,r,g,b);

		setPix();
	}

	public void setPix(){
		// x軸の半分の地点をxc,y軸の半分の地点をycとする
		int xc = w/2;
		int yc = h/2;
		// 円の半径をrとする
		double r = 2;
		for(int z=0;z<20;z++){
			for (int y=1;y<h;y++) {
				for (int x=1;x<w;x++){
					// y*h+xでy列目x行目を示す
					int p = y*w+x;
					// 中心(xc,yc)からの距離
					double equ = Math.sqrt((xc-x)*(xc-x)+(yc-y)*(yc-y));
					// r と 距離が一致した箇所を塗る.
					// この時equをintにすることで大体の場所を塗りつぶすようになる
					if (equ == r){
						pix[p]=1;
					}
				}
			}
			r+=10;
		}
	}


	public void paint(Graphics g) {
		// (objectの横幅、objectの縦幅、カラーモデル、元となるint型配列、データオフセット、スキャンサイズ)
		MemoryImageSource ming = new MemoryImageSource(w,h,cm,pix,0,w);
		img = createImage(ming);

		g.drawImage(img,10,0,this);
		}
		}
