from mpi4py import MPI


if __name__ == '__main__':
    comm = MPI.COMM_WORLD
    rank = comm.Get_rank()

    if rank == 0:
        var = {'temp':  10,
               'pressure': 25}
        comm.send(var, dest=1, tag=11)

    elif rank == 1:
        var = comm.recv(source=0, tag=11)

    print(var)


