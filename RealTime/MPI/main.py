import numpy as np
from numpy import linalg as LA
from time import time
import os


SIZE = 1000

if __name__ == '__main__':
    print(f'CPU number: {int(os.getenv("SLURM_CPUS_PER_TASK", 1))}')
    print(f'Threads number: {int(os.getenv("OMP_NUM_TREADS", 1))}')
    print(f'Tasks number: {int(os.getenv("SLURM_NTASKS", 1))}')
    print()
    
    NR = 5
    t_initial = time()
    for i in range(NR):
        t_task_initial = time()
        p1 = np.random.random([SIZE, SIZE])
        p1 += p1.T
        p2 = LA.pinv(p1)

        delta_t_start = time() - t_initial
        delta_t_task = time() - t_task_initial
        print(f'all time: {delta_t_start:.2f} seconds')
        print(f'task time: {delta_t_task:.2f} seconds')
