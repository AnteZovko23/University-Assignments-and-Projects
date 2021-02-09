#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>
#include <unistd.h>

/**
 * Author: Ante Zovko
 * Version: February 8th, 2021
 * 
 * Dinning Philosophers’ problem in pthreads
 * 
 * Run command for linux:gcc PhilosophersProblem.c -lpthread
 * 
 */

pthread_t *philosopher;
pthread_mutex_t *chopstick;

int numberOfTimesToEat;
int numOfChopsticks;

// Tracks each philosopher's eating process
void *func(void *val) {

    int n = (long) val;

    printf("Philosopher %d is thinking\n", n);

    pthread_mutex_trylock(&chopstick[n]);
    pthread_mutex_trylock(&chopstick[(n+1)%numOfChopsticks]);

    printf("Philosopher %d is eating \n", n);
    sleep(3);

    int sleeptime=rand()%10;

    pthread_mutex_unlock(&chopstick[n]);
    pthread_mutex_unlock(&chopstick[(n+1)%numOfChopsticks]);

    printf("Philosopher %d finished eating in %d seconds \n", n, sleeptime);

    return(NULL);


}

// Get random numbers between bounds
int getRandoms(int lower, int upper) { 
        int num = (rand() % 
           (upper - lower + 1)) + lower; 

        return num;
} 



int main(int argc, char *argv[]) {


    // Get size and number of times to eat from command line
    int size = atoi(argv[2]);
    numberOfTimesToEat = atoi(argv[1]);
    int numberOfTimesToEatTracker[size];

    numOfChopsticks = size;

    // Philosopher and chopstick arrays
    philosopher = (pthread_t*)malloc(sizeof(pthread_t)*size);
    chopstick =(pthread_mutex_t*)malloc(sizeof(pthread_mutex_t)*size);

    srand(time(NULL));
    int i;

    // Initialize the mutex
    for(i = 0; i < size; i++)
        pthread_mutex_init(&chopstick[i], NULL);


    int loopCounter = 0;

    // Executes processes
    while(loopCounter < (size * numberOfTimesToEat)) {
        
        // Get random philosopher to eat
        i = getRandoms(0, size - 1);

        // If the philosopher already ate enough times, it skips him
        if(numberOfTimesToEatTracker[i] < numberOfTimesToEat){

            pthread_create(&philosopher[i], NULL, (void*)func, (void*)(long)i);
            pthread_join(philosopher[i], NULL);

            printf("Philosopher %d has eaten %d times.\n\n", i, numberOfTimesToEatTracker[i]+1);


            numberOfTimesToEatTracker[i]++;
            loopCounter++;
        
        }
        

    }

    exit(1);

}