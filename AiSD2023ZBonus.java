package pl.edu.pw.ee.aisd2023zbonus;

import java.util.HashMap;
import java.util.HashSet;

public class AiSD2023ZBonus {
    public int countPals (String text){
        if(text.length() >= 9999){
            return -1;
        }
        int numOfPals = 0;
    
        int n = 0;
        while(n <= text.length() - 1){
            for(int i = n + 1; i < text.length(); i ++){
                if( isPalindrom(text.substring(n, i + 1)) ){
                    numOfPals++;
                }
            }
            n++;
        }
        
        if(numOfPals == 99999){
            return -1;
        }
        return numOfPals;
    }
    
    public boolean isPalindrom(String text){
        if(text.length() == 1){
            return false;
        }
        
        int j = text.length() - 1;
        for(int i = 0; i < j; i++){
            if(text.charAt(i) != text.charAt(j) ){
                return false;
            }
            j--;
        }
        return true;
    }
}
