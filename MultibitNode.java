import java.util.Arrays;

public class MultibitNode {
    String nextHop = null;
    boolean isPrefix = false;
    private int strideLength = 4;
    MultibitNode[] nextNode = new MultibitNode[(int) Math.pow(2,strideLength)];

    static MultibitNode insert(MultibitNode n, String prefix, int index, String nextHop){

        int nodeIndex;
        String p = index+4 >= prefix.length()? prefix.substring(index):prefix.substring(index,index+4);
        nodeIndex = Integer.parseInt(p, 2);
        //System.out.println(nodeIndex);
        //nodeIndex = Integer.parseInt(fourBits,2);

        if(n == null) {
            n = new MultibitNode();
        }

        if(index+4 == prefix.length() || p.length() != 4){

            //set prefix
            n.isPrefix = true;
            //set next hop
            n.nextHop = nextHop;
            return n;

        } else {
            n.nextNode[nodeIndex] = insert(n.nextNode[nodeIndex],prefix,index+4,nextHop);
        }
        return n;
    }
//
//    static void print(MultibitNode n){
//
//        System.out.println(Arrays.toString(n.nextNode));
//        System.out.println(Arrays.toString(n.nextNode[0].nextNode));
//        System.out.println(Arrays.toString(n.nextNode[0].nextNode[1].nextNode));
//        System.out.println(Arrays.toString(n.nextNode[0].nextNode[1].nextNode[0].nextNode));
//    }

}
