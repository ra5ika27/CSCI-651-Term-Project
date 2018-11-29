import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Improved_Main {


    private static String ipToPrefix(int subnet, String ipAddress){

        String[] splitIp = ipAddress.split("\\.");
        String networkPrefix = "";

        for(String ipPart: splitIp){

            if(networkPrefix.length() != subnet) {
                int intIp = Integer.parseInt(ipPart);
                networkPrefix += String.format("%8s",Integer.toBinaryString(intIp)).replace(" ","0");
            }

        }
        return networkPrefix;
    }

    public static void main(String[] args) {

        BufferedReader br = null;
        Improved_Node root = null;
        try {

            br = new BufferedReader(new FileReader("/Users/rasikathorat/IdeaProjects/FCN/src/CSCi-651-Term-Project/dataset.txt"));
            String c;
            while((c = br.readLine())!=null){

//                String[] split = c.split("\\s+");
//                StringBuilder ipAddress = new StringBuilder();
//                int i;
//                for(i=0;i<split[0].length()-3;i++){
//                    ipAddress.append(split[0].charAt(i));
//                }
//
//                int subnet = Integer.parseInt(c.substring(i+1,split[0].length()));
//                String nextHop = c.substring(20,c.length());
//                String netPrefix = ipToPrefix(subnet, ipAddress.toString());

                String nextHop = "";
                String ipAddress = "";
                String subnet = "";
                int start = 0;
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
                System.out.println(nextHop);
                System.out.println(subnet);
                String netPrefix = ipToPrefix(Integer.parseInt(subnet),ipAddress);
                System.out.println(netPrefix);
                if(root == null)
                    root = Improved_Node.insert(null, netPrefix, 0, nextHop);

                else
                    root = Improved_Node.insert(root,netPrefix,0,nextHop);
            }

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
