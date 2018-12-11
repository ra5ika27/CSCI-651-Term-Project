/**
 * This class is an implementation of Hirochika Asai
 * and Yasuhiro Ohara's Poptrie lookup. The lookup
 * is implemented based on the pseudo code in the
 * paper.
 *
 * @author: Sudheeksha Garg
 * @author: Rasika Thorat
 * @author: Hanna Miller
 */

import java.util.ArrayList;

public class Poptrie_Lookup {

    /**
     * This function takes the root of the poptrie node,
     * the internal array N and external array L, and the
     * IP address as the input and transverses through
     * the trie. Once, it finds a leaf node, the value
     * in the leaf node is displayed.
     *
     * @param root
     * @param N
     * @param L
     * @param key
     */
    void lookup( Poptrie_Node root, ArrayList<Poptrie_Node> N, ArrayList<String> L, String key){

        //Set the start time for the lookup
        long start = System.nanoTime();

        try {
            int base, bc;

            //The index of the root is 0.
            int index = 0;

            //Acquire the current vector value.
            int[] vector = root.vector;

            //Initial offset is set to 0.
            int offset = 0;

            //extract most significant bits from IP address
            //and convert it to decimal
            int v = extract(key, offset);

            //Acquire current base1 value
            base = root.base1;

            //As long as the value of vector position
            //is not 0, loop.
            while (1 == vector[15 - v]) {

                //Acquire the number of 1's in
                //the least significant bits
                bc = popCount(v, vector);

                //The base + bc - 1 gives the new index value.
                index = base + bc - 1;

                //Acquire the new vector value
                vector = N.get(index).vector;

                //Increase the offset by 4.
                offset += 4;

                //Extract the current significal bits and
                //get its equivalent decimal value.
                v = extract(key, offset);

                //Get current the base1 value.
                base = N.get(index).base1;
            }


            //Get the base0 value
            base = N.get(index).base0;

            //Calculate number of zeros in
            //least signoficant n+1 bits
            bc = popCount0(v, vector);

            //Display the next hop.
            System.out.println("Next hop " + L.get(base + bc - 1));

        }catch (Exception e){
            //Exception in case no IP address is found.
            System.out.println("IP address not found "+key);

        }

        //Set the end of lookup time
        long end  = System.nanoTime();

        //Display time taken for looking up IP address.
        System.out.println("Lookup time "+ (end-start));

    }

    /**
     * This function calculates the number of ones
     * from a given position v in the vector array.
     * @param v
     * @param vec
     * @return
     */
    int popCount(int v, int[] vec){
        int count = 0;
        for (int i = 15 - v; i < 16; i++) {
            if(vec[i] == 1)
                count++;
        }

        return count;
    }

    /**
     * This function calculates the number of zeros
     * from a given position v in the vector array.
     * @param v
     * @param vec
     * @return
     */
    int popCount0(int v, int[] vec){
        int count = 0;
        for (int i = 15 - v; i < 16; i++) {
            if(vec[i] == 0)
                count++;
        }

        return count;
    }

    /**
     * This function extracts a chunk of data from the
     * IP address and converts it to decimal and returns it.
     * @param key
     * @param offset
     * @return
     */
    int extract(String key, int offset){
        return Integer.parseInt(key.substring(offset, offset + 4), 2);
    }

}
