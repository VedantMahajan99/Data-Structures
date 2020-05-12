// File Name: Sieve_of_Erotosthenes.java
// Author: Vedant Mahajan
// Course : CS 3345.004
// Modification History
// This code was written on 1st Feburary 2020.
// Procedures:
// main: Prompts user to enter number , runs Sieve of Erotosthenes algorithm and finds all prime numbers till entered number.

import java.util.Scanner;

public class Sieve_of_Erotosthenes {
    
    public static void main(String[] args) {
        
        Scanner scanner_obj = new Scanner(System.in);
        
        System.out.print("Enter a value to find all prime numbers till that number : ");
        
        int N = scanner_obj.nextInt();
        
        boolean prime[] ;
        
        prime = new boolean[N];
        
        for(int i = 2 ; i <= Math.sqrt(N) ; i++ )
        {
            if(prime[i] == false)
            {
                for(int j = i*i ; j < N ; j= j + i )
                {
                    prime[j] =  true;
                }
            }
        }
        
        for(int k=2 ; k < N ; k++)
        {
            if(prime[k] == false)
            {
                System.out.println(k);
            }
        }
       
        
    }
    
}
