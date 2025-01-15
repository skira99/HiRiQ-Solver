package main.java;

public class HiRiQ{
  //int is used to reduce storage to a minimum...
  private int config;
  private byte weight;

  //initialize to one of 5 reachable START config n = 0,1,2,3,4
  HiRiQ(byte n){
    switch(n){
      case 0:
        config = 65536/2;
        weight = 1;
        break;
      case 1:
        config = 1626;
        weight = 6;
        break;
      case 2:
        config = -1140868948;
        weight = 10;
        break;
      case 3:
        config = -411153748;
        weight = 13;
        break;
      case 4:
        config = -2147450879;
        weight = 32;
        break;
      case 5:
        config = -2147483647;
        weight = 33;
        break;
    }
  }


  public int getConfig(){
    return config;
  }
  
  public byte getWeight(){
    return weight;
  }
  
  boolean IsSolved(){
    return( (config == (65536/2) ) && (weight == 1) );
  }

  //transforms the array of 33 booleans to an (int) config and a (byte) weight.
  public void store(boolean[] B){
    int a = 1;
    config = 0;
    weight = (byte) 0;
    
    if (B[0]){
      weight++;
    }
    
    for(int i = 1; i < 32; i++){
      if(B[i]){
        config = config + a;
        weight++;
      }
      a = 2*a;
    }
    if(B[32]){
      config = -config;
      weight++;
    }
    return;
  }

  //transform the int representation to an array of booleans.
  //the weight (byte) is necessary because only 32 bits are memorized
  //and so the 33rd is decided based on the fact that the config has the
  //correct weight or not.
  public boolean[] load(boolean[] B){
    byte count = 0;
    int fig = config;
    B[32] = fig<0;
    
    if(B[32]){
      fig= -fig;
      count++;
    }
    
    int a = 2;
    
    for (int i = 1; i < 32; i++){
      B[i] = (fig%a) > 0;

      if(B[i]){
        fig = fig - (a/2);
        count++;
      }
      
      a = 2*a;
    }

    B[0] = count < weight;
    
    return(B);
  }
    
  //prints the int representation to an array of booleans.
  //the weight (byte) is necessary because only 32 bits are memorized
  //and so the 33rd is decided based on the fact that the config has the
  //correct weight or not.
  public void printB(boolean Z){
    if(Z){
      System.out.print("[@]");
    } 
    else{
      System.out.print("[ ]");
    }
    return;
  }
    
  public void print(){
    byte count = 0;
    int fig = config;
    boolean next,last = fig < 0;

    if(last){
      fig = -fig;
      count++;
    }
    
    int a = 2;
    
    for (int i = 1; i < 32; i++){
      next = (fig%a) > 0;
      
      if(next){
        fig = fig - (a/2);
        count++;
      }
      
      a = 2*a;
    }
    
    next = count < weight;
    count = 0;
    fig = config;

    if(last){
      fig = -fig;
      count++;
    }
    
    a = 2;

    System.out.print("      "); 
    printB(next);

    for(int i = 1; i < 32; i++){
      next = fig%a>0;
      if(next){
        fig = fig-a/2;
        count++;
      }
      
      a = 2*a;
      printB(next);
      if(i == 2 || i == 5 || i == 12 || i == 19 || i == 26 || i == 29){
        System.out.println();
      }
      if(i == 2 || i == 26 || i == 29){
        System.out.print("      ");
      }
    }
    printB(last); 
    System.out.println();
    return;
  }
}