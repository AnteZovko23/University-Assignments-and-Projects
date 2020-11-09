#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>

// Gets random numbers based on upper and lower limits
int getRandoms(int lower, int upper) { 
        int num = (rand() % 
           (upper - lower + 1)) + lower; 

        return num;
} 

/**
 * Authors: Ante Zovko and Timothy Oliver
 * Version: November 9, 2020
 * 
 * Creates a forked child process that executes an executable
 * that calculates the product of given command line arguments
 * 
 */
int main(int argc, char *argv[]){

    
    srand(time(0));
    if(argc == 1){

        printf("Usage: %s <executables>\n", argv[0]);
        printf("e.g., %s ./one\n", argv[0]);

    }
    else {
        pid_t pid = fork();

    if(pid == 0){

        pid_t childPID = getpid();

        char buffer[20];
        
        int argNumber = getRandoms(1, 10);
        char *args[argNumber + 2];
        args[0] = argv[1];



        for(int i = 1; i < argNumber + 1; i++){

            int ranNumber =  getRandoms(0,10);
            sprintf(buffer, "%d", ranNumber);
    
            args[i] = strdup(buffer);

        }

        args[argNumber + 1] = NULL;

        printf("In the child %d :", childPID);
        fflush(stdout);
        execvp(args[0], args);

        exit(EXIT_SUCCESS);

    }
    else if(pid > 0){

        wait(NULL);

    }
    }
    

    return 0;
}