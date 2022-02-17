#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>

/**
 * Author: Ante Zovko
 * Version: November 9th, 2020
 * 
 * Forks a child process and uses one of the exec functions to list the contents of a
    directory specified as an argument at the command line
 * 
 */
int main(int argc, char *argv[]){

    if(argc == 1){

        printf("Usage: %s <dir>\n", argv[0]);

    }
    else {

        pid_t pid = fork();

        if(pid == 0){

            char* cwd = getcwd(NULL, 0);
            printf("\nCurrent working directory: %s", cwd);

            printf("\nExecuting ls %s --all -l -human-readable\n", argv[1]);

            if(chdir(argv[1]) == 0){

                char* cmd[] = { "ls", "-a", "-l", "-h", NULL };
                execvp("ls", cmd);
                exit(EXIT_SUCCESS);

            }
            else {

                printf("Can't chdir to %s", argv[1]);
                exit(EXIT_FAILURE);

            }
        }
        else if(pid > 0){
            int status;
            waitpid(pid, &status, 0);


            printf("\nExit status: %d\n", WEXITSTATUS(status));

        }
        
       
        


    }

}