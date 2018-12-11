/**This class simulates the population of
 * network prefixes from a given text file
 * in the Poptrie data structure and looks
 * up a given IP address. It performs
 * population with leaf compression.
 *
 * @author: Sudheeksha Garg
 * @author: Rasika Thorat
 * @author: Hanna Miller
 *
 */

import java.io.*;
import java.util.ArrayList;

public class Poptrie_MainLeaf {

    /**
     * This function converts a IP address
     * to its respective network prefix for
     * a given subnet mask.
     * @param subnet
     * @param ipAddress
     * @return
     */
    private static String ipToPrefix(int subnet, String ipAddress){

        String[] splitIp = ipAddress.split("\\.");
        String networkPrefix = "";
        String binaryIp = "";

        for(String ipPart: splitIp) {

            int intIp = Integer.parseInt(ipPart);
            binaryIp += String.format("%8s", Integer.toBinaryString(intIp)).replace(" ", "0");
        }
        networkPrefix = binaryIp.substring(0,subnet);
        return networkPrefix;
    }

    /**
     * This function runs through the given file
     * and calls appropriate functions to
     * populates the Poptrie data structure.
     * @param args
     */
    public static void main(String[] args) {

        BufferedReader br;
        Poptrie_NodeLeaf root = null;
        try {

            br = new BufferedReader(new FileReader("Test_file.txt"));
            String c;

            //run through the file and extract IP address
            //and next hop value.
            while((c = br.readLine())!=null){
                String nextHop;
                String ipAddress = "";
                String subnet = "";
                char ch = c.charAt(0);

                int i = 0;
                while(ch != '/'){
                    ipAddress += ch;
                    i ++;
                    ch = c.charAt(i);
                }
                System.out.println(ipAddress);
                i++;
                ch = c.charAt(i);
                while(ch != ' '){
                    subnet += ch;
                    i++;
                    ch = c.charAt(i);
                }

                nextHop = c.substring(i+1,c.length());
                String netPrefix = ipToPrefix(Integer.parseInt(subnet),ipAddress);
                if(root == null) {
                    //if root is null, create a root.
                    root = Poptrie_NodeLeaf.insert(null, netPrefix, 0, nextHop);

                }else{
                    //Insert a network prefix
                    root = Poptrie_NodeLeaf.insert(root, netPrefix, 0, nextHop);
                }
            }

            //create a leaf array
            ArrayList<String> L = new ArrayList<>();

            //create an internal node
            ArrayList<Poptrie_NodeLeaf> N = new ArrayList<>();

            //Populate with L, and N.
            Poptrie_populationLeaf.populate(root,L,N);

            //Get string for lookup.
            String lookup_Ip = "223.255.254.0";
            if(args.length!=0){
                lookup_Ip = args[0];
            }

            //Get network prefixes for the next hop
            String key = ipToPrefix(32,lookup_Ip);

            Poptrie_LookupLeaf pl = new Poptrie_LookupLeaf();

            //Lookup key in the Poptrie data structure
            pl.lookup(root, N, L, key);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
