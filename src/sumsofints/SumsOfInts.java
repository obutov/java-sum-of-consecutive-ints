/*
 * Program for finidng sums of non-negative consequtive integers
 */
package sumsofints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author onb
 */
public class SumsOfInts {

    /**
     * O(sqrt(n))
     */
    public static void main(String[] args) {        
        System.out.println("Enter number: ");
        Scanner in = new Scanner(System.in);
        long n = in.nextInt();
                
        long k = 1; // min sequence size, k != 0
        List<Long> seqs = new ArrayList();
        
        double limit = (Math.sqrt(8 * n + 1) - 1) / 2; // for a = 1 [k = (sqrt(4a^2 - 4a + 8n +1) - 2a + 1) /2 ]
        while (k <= limit){ 
            // find begining of a sequence - a for k
            // a = (2*n/k - k + 1)/2
            // n = a + (a+1) + (a+2) + ... + (a + (k-1))
            // n = sum (a + i), where i -> (0, k-1)
            // n = sum (a) + sum (i), where i -> (0, k-1)
            // n = ka + (k*(k-1))/2
            // n = k * ((2a+k-1)/2)
            // if k is odd, n should be devisible by k
            // if k is even, n should be devisible by k/2
            if ((k % 2 != 0 && n % k == 0) || (k % 2 == 0 && n % (k / 2) == 0)){
                long a = getA(k, n);
                // only take a > 0 and a <=n, inlude distinct a values
                if (a > 0 && a <= n && !seqs.contains(a))
                    seqs.add(a);
            }
            k++;
        }
        
        // print out the result
        Collections.sort(seqs);
        
        System.out.println(String.format("Found %d sequences:", seqs.size()));
        seqs.stream().map((start) -> {
            long sum = 0;
            while (sum+start <=n){
                if (sum != 0) System.out.print("+");
                System.out.print(start);
                sum += start;
                start++;
            }
            return sum;
        }).forEachOrdered((Long sum) -> {
            System.out.println("="+sum);
        });        
    }
    
    public static long getA(long k, long n){  
        // result should be a whole number
        if ((2*n/k - k + 1) % 2 == 0)
            return (2*n/k - k + 1)/2;
        // otherwise return -1
        return -1;
    }                
}
