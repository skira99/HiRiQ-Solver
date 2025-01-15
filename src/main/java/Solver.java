package main.java;

public class Solver{
  public static void main(String args[]){
    HiRiQ container = new HiRiQ((byte) 0);
    
    // boolean[] b = new boolean[]{true, true, true, 
    //                             true, true, true, 
    //                 true, true, true, true, true, true, true, 
    //                 true, true, true, true, true, true, true, 
    //                 true, true, true, true, true, true, true, 
    //                             true, true, true, 
    //                             true, true, true};

    boolean[] b = new boolean[]{false, true, true, 
                                false, false, false, 
                  false, false, false, false, false, false, false, 
                  false, false, false, false, false, false, false, 
                  false, false, false, false, false, false, false, 
                                false, false, false, 
                                false, false, false};                     
    
    container.store(b);
    container.print();
    System.out.println();

    Node test = new Node(b,"", (byte) 2);

    container.store(test.getCurrent());
    container.print();
    System.out.println();
    
    test.genChildren();

    container.store(test.getCurrent());
    container.print();
    System.out.println();

    container.store(test.getChild(0));
    container.print();
    return;
  }
}