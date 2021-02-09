
#include <stdio.h>
#include <string.h> 

#define MAX 256

/**
 * Author: Ante Zovko
 * Partner: Timothy Oliver
 * 
 * Simulates a linux terminal, describing each input from the user
 * 
 */   
int main(){

    // The userInput array and token array
    char inputArr[MAX];
    char tokenArr[MAX];

    
    while(1){

        printf("\n$ ");
        

        fgets(inputArr, MAX, stdin);

        // If the input is exit, then break loop
        int comp = strcmp(inputArr, "exit\n");
        if(comp == 0)
            break;
        
        // Print the line
        printf("Line read: ");
        printf(" %s", inputArr); 

        // Split the string based on spaces
        char* tokens = strtok(inputArr, " ");
        
        // While the array has tokens, print them out
        // Count the number of them and print it out
        printf("Token(s):");
        int counter = 0;
        while (1) {

            if(tokens == NULL)
                break;
            printf("\n %s", tokens); 
            tokens = strtok(NULL, " "); 
            counter++;

        } 
        printf("%d token(s) read\n", counter);
     
    }


    return 0;
}