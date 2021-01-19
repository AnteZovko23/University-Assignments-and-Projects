from time import time

from heapsort import *

"""
Author: Ante Zovko
Description: Shows the Executions times of 4 example functions
Version: 12/15/2020

"""

# Calls given functions
def functionRunner(function, numberOfFunctionCalls = 1):

    # Starting size of list
    size = 50
    S = list(range(size))

    # Number of calls
    for i in range(1, numberOfFunctionCalls + 1):

        startTime = time()
        function(S)
        endTime = time()

        print("Execution time: {}. run = {:.10f}".format(i, endTime - startTime))

        # Increase list size
        size *= 10
        S = list(range(size))

    # Reset list size for next function
    size = 50
    S = list(range(size))
    print()



"""
    The following code will run a single function.
    Repeat similar codes for all four functions.
    Make sure to use the same input for each function execution.
    Execute multiple runs with different sizes of input.
    
    start_time = time()
    S = range(10)
    example1(S)
    end_time = time()
    T_time = end_time - start_time
    print("Execution time = {}".format(T_time))
    """
if __name__ == '__main__':

    
    functionRunner(heapSort, 6)

       
    
    
