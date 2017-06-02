import java.util.Random;
import java.util.Arrays;

public class PartSerch2{
	public static void main(String args[]){
	/*問題のデータを代入*/
		int data[][];
		data = new int[4][4];
		int count = 0;
		int dataInput[] = {6,1,9,3,2,5,7,8,6,3,5,4,3,5,2,1}; //左上から右下へのデータ
		for(int i = 0; i <= 3 ; i ++){
			for(int j = 0; j <= 3 ; j++){
				data[i][j] = dataInput[count];
				count ++;
			}
		}
		
		/*初期解の生成
		Random rand = new Random();
		int num = rand.nextInt(4) + 1;
    	*/
    	int setAns1[] = new int[4];
		int setAns2[] = new int[4];
		int setAns3[] = new int[4];
		int setAns4[] = new int[4];
		int setAns5[] = new int[4];

		setAns1 = GeneNum(setAns1);
		setAns2 = GeneNum(setAns2);
		setAns3 = GeneNum(setAns3);
		setAns4 = GeneNum(setAns4);
		setAns5 = GeneNum(setAns5);

    	/*最適の比較*/
    	int loop = 0;
    	int bestTime = 10000;
    	int bestCombi[] = {};
    	int combi[];
    	int time = 0;
    	
    	while(loop < 4){
    	
    	time = Calc(setAns1,data);
    	combi = setAns1.clone();
      	if(bestTime >= time){
      		bestTime = time;
      		bestCombi = combi;
      	}
      	time = Calc(setAns2,data);
    	combi = setAns2.clone();
      	if(bestTime >= time){
      		bestTime = time;
      		bestCombi = combi;
      	}
		time = Calc(setAns3,data);
    	combi = setAns3.clone();
      	if(bestTime >= time){
      		bestTime = time;
      		bestCombi = combi;
      	}
      	time = Calc(setAns4,data);
    	combi = setAns4.clone();
      	if(bestTime >= time){
      		bestTime = time;
      		bestCombi = combi;
      	}
      	time = Calc(setAns5,data);
    	combi = setAns5.clone();
      	if(bestTime >= time){
      		bestTime = time;
      		bestCombi = combi;
      	}

       	System.out.println("bestTime: "+bestTime+"\t bestCombi: "+Arrays.toString(bestCombi)+"\n");
      	
      	/*摂動*/
    	ChangeArray(setAns1);
    	ChangeArray(setAns2);
    	ChangeArray(setAns3);
    	ChangeArray(setAns4);
    	ChangeArray(setAns5);
    	loop++;
    	}
    	
	}

		/*データの計算*/
		public static int Calc(int[] ans, int data[][]){
			int result = 0;
			String subject[] = {"英語","数学","物理","化学"};
			for(int i = 0; i <= 3; i++){
				int tmp = ans[i] - 1 ;
				System.out.println((i+1)+"人目\t 選択:\t"+subject[ans[i]-1]+ans[i]+"\t 時間:\t"+data[i][tmp]);
				result += data[i][tmp];
			} 
			System.out.println("かかる時間は"+result);   	
			return result;
		}
		
		/*摂動の決定*/
		/*今回は1->2,2->3のように値に+1をする. ただし4の時は1に戻る*/
		public static void ChangeArray(int[] ans){
			for(int i = 0;i <= 3;i++){
				int tmp = ans[i];
				if(tmp ==4){
					ans[i] = 1;
				}else{
					tmp += 1;
					ans[i]= tmp;
				}
			}
		}
		
		/*乱数データの生成*/
		public static int[] GeneNum(int[] answer){
    		Random rand = new Random();
    		for(int i = 0; i < answer.length; i++){
       			Loop: while(true){
            		answer[i] = (int)(rand.nextInt(4)+1);  //1~4の数値を入れる
            	for(int j = 0; j < i; j++){
                //その前までの数値のどれかとかぶっていたら数値代入からやり直し
                	if(answer[j] == answer[i]) continue Loop;
            	}
            		break;
        		}
  		 	}
  		 	System.out.println("初期解は\t"+Arrays.toString(answer));
    		return answer;
		}	

}