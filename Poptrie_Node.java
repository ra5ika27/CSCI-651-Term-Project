import java.util.Arrays;

public class Poptrie_Node {

    static int STRIDE_LENGTH = 4;
    String[] nextHop = new String[(int) Math.pow(2,STRIDE_LENGTH)];
    boolean isLeaf = false;
    Poptrie_Node[] nextNode = new Poptrie_Node[(int) Math.pow(2,STRIDE_LENGTH)];
    int[] vector = new int[(int)Math.pow(2,STRIDE_LENGTH)];
    int base0 = -1; // offset for leaf Array L
    int base1 = -1; // offset for Internal node Array N

    private Poptrie_Node() {

        isLeaf = false;
        for(int i=0;i<nextHop.length;i++){
            nextHop[i] = "";
        }
        base0 = -1;
        base1 = -1;
        for(int i=0;i<vector.length;i++) {
            vector[i] = -1;
        }
    }

    static Poptrie_Node insert(Poptrie_Node n, String prefix, int index, String nextHop){

        int nodeIndex;
        String p;
        boolean less = false;
        if(index+STRIDE_LENGTH >= prefix.length()){
            p=prefix.substring(index);
            less = true;
        } else {
            p = prefix.substring(index,index+STRIDE_LENGTH);
        }
        nodeIndex = 15 - Integer.parseInt(p, 2);
  //      System.out.println(nodeIndex);

        if(n == null) {
            n = new Poptrie_Node();
        }

        if(index+STRIDE_LENGTH == prefix.length() || less /*p.length() != STRIDE_LENGTH*/){

            //set prefix
            n.isLeaf = true;
            //set next hop
            n.nextHop[nodeIndex] = nextHop;

            n.vector[nodeIndex] = 0;
            return n;

        } else {

            n.vector[nodeIndex] = 1;
            n.nextNode[nodeIndex] = insert(n.nextNode[nodeIndex],prefix,index+STRIDE_LENGTH,nextHop);
//            System.out.println(Arrays.toString(n.nextNode));
//            System.out.println(Arrays.toString(n.vector));
        }

        return n;
    }

//    static void print(MultibitNode n){
//
//        System.out.println(Arrays.toString(n.nextNode));
//        System.out.println(Arrays.toString(n.nextNode[0].nextNode));
//        System.out.println(Arrays.toString(n.nextNode[0].nextNode[1].nextNode));
//        System.out.println(Arrays.toString(n.nextNode[0].nextNode[1].nextNode[0].nextNode));
//    }

}
