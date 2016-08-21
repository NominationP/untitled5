import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import com.sun.jndi.toolkit.ctx.Continuation;

import oracle.jrockit.jfr.tools.ConCatRepository;

public class ScretTask_1046{

    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){

            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        String string = reader.nextLine();

        ArrayList<String> list = new ArrayList<String>();

        ArrayList<Integer> list1 = new ArrayList<Integer>();

        for(int i=0; i<string.length(); i++){
            list.add(string.valueOf(string.charAt(i)));
        }

        String first = list.get(0);
        int c = 0;
        int wenhao =0;
        int zimu =0;
        for(int i=0; i<list.size()-1; i++){

            if(list.get(i).equals(list.get(i+1)) && !isNumeric(list.get(i))){
                c++;
            }else{
                break;
            }

        }
        int ten=10;
        if(first.equals("?")){
            wenhao++;
            list1.add(9);
				
				
	
				
				/*for(int j = 0 , len= c;j<=len;++j){
					  if(list.get(j).equals("?")){
					       list.remove(j);
					       --len;//å��å°�ä¸�ä¸ª
					       --j;//å¤�è°¢deny_guoshouæ��æ­£ï¼�å¦�æ��ä¸�å� ä¼�å�ºç�°è¯�è®º1æ¥¼æ��è¯´ç��æ��å�µã��
					 }
					}*/
        }else if(!isNumeric(first)){

            list1.add(9);
            ten--;
				
				/*for(int j = 0 , len= c;j<=len;++j){
					  if(list.get(j).equals(first)){
					       list.remove(j);
					       --len;//å��å°�ä¸�ä¸ª
					       --j;//å¤�è°¢deny_guoshouæ��æ­£ï¼�å¦�æ��ä¸�å� ä¼�å�ºç�°è¯�è®º1æ¥¼æ��è¯´ç��æ��å�µã��
					 }
					}*/
        }






        Collections.sort(list);



        for(int i=0; i<list.size(); i++){

            if(list.get(i).equals("?")){
                if(wenhao>0){
                    wenhao--;
                    continue;
                }else{
                    list1.add(10);
                    continue;
                }



            }


            if(isNumeric(list.get(i))){
                continue;
            }






            if(i==list.size()-1 ){
                if(list.get(i).equals(first)){
                    continue;
                }


                if(list.get(i-1).equals(list.get(i))){
                    continue;
                }else{
                    list1.add(ten);
                    continue;
                }
            }

            if(list.get(i).equals(list.get(i+1)) ){


                if(list.get(i).equals(first)){
                    continue;
                }


                if(i==0){

                }
                if(i+1 == list.size()-1){
                    list1.add(ten);
                }
                continue;
            }else{
                if(list.get(i).equals(first)){
                    continue;
                }
                list1.add(ten);
                ten--;
                continue;
            }
        }

        BigInteger result = new BigInteger("1");

        for (Integer integer : list1) {
            result = result.multiply(BigInteger.valueOf(integer));
        }

        System.out.println(result);


    }

}