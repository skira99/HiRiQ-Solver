package main.java;
import java.util.ArrayList;

public class HiRiQ_Solver{

  private static  int[][] triplets = {{0,1,2}, {3,4,5}, {6,7,8}, {7,8,9}, {8,9,10}, {9,10,11}, {10,11,12}, {13,14,15}, {14,15,16}, {15,16,17}, {16,17,18}, {17,18,19}, {20,21,22}, {21,22,23}, {22,23,24}, {23,24,25}, {24,25,26}, {27,28,29}, {30,31,32}, {12,19,26}, {11,18,25}, {2,5,10}, {5,10,17}, {10,17,24}, {17,24,29}, {24,29,32}, {1,4,9}, {4,9,16}, {9,16,23}, {16,23,28}, {23,28,31}, {0,3,8}, {3,8,15}, {8,15,22}, {15,22,27}, {22,27,30}, {7,14,21}, {6,13,20}};
  
  public static void main(String args[]) {
    HiRiQ container = new HiRiQ((byte) 0);
    // boolean[] b =  new boolean[33];

    // boolean[] b = new boolean[]{true, true, true,
    //                             true, true, true,
    //                 true, true, true, true, true, true, true,
    //                 true, true, true, false, true, true, true,
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
  
    Node test = new Node(b,"", (byte) 2);
    test.genChildren();
    
    ArrayList<Node> family = new ArrayList<Node>();
    family = test.getChildren();
    
    int len = family.size();
    for(int i = 0; i < len; i++){
      System.out.println(family.get(i).getMove());
      container.store(family.get(i).getCurrent());
      container.print();
      System.out.println();
    }
  }

  public static int[] getTriplet(int index){
    return triplets[index];
  }
}