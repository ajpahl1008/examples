package com.pahlsoft.testdatabuilder.util;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomDollarAmountUtil {
	
	  private Random fRandom = new Random();
      DecimalFormat df = new DecimalFormat("#.##");
      
       public RandomDollarAmountUtil() {
             
       }

	    public String getRandomDollarAmount(){
           double _dbl = fRandom .nextInt(99999) + fRandom.nextGaussian();
           
           if ( _dbl < 0 ) {
                  return df.format(_dbl*-1); // if random returns negative
           } else {
                  return df.format(_dbl);
           }
	    }

}


