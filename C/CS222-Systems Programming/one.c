#include <stdio.h>
#include <stdlib.h>
#include <string.h> 

/**
 * Authors: Ante Zovko and Timothy Oliver
 * Version: November 9, 2020
 * 
 * Calculates products of command line arguments
 * 
 */ 
int main(int argc, char *argv[]){

    int result = 1;


    if(argc == 1){

        printf("Usage: %s val [val [...]]", argv[0]);
        printf("\ne.g., %s 17 49 3 466\n", argv[0]);


    }
    else {

        for(int i = 1; i < argc; i++){

            result *= atoi(argv[i]);
        }
        
        printf(" The product of ");
        for(int i = 1; i < argc; i++){

            printf(" %d ", atoi(argv[i]));

        }

        printf("is %d\n", result);

        }
    

    return 0;
}