import PyQt5

def something():
    try:
        UpdateCacheCap(cache)
    except ValueError:
        pass
    cach_cap_record.delete(0, 'end')

def UpdateCacheCap(cache):
    cache_cap_txt['state'] = 'standard'
    cache_cap_txt.delete('1.0', 'end')
    print_cache = []

    for elements in cache:
        print_cache.append(elements[0])
    
    cache_cap_txt.insert('1.0', '\n'.join(print_cache))
    cache_cap_txt['state'] = 'disabled'

def ChangeAlgorithmType(alg_type):
    global alg_var
    alg_var = alg_type

def ChangeCurrentFile(filename):
    with open(filename) as fn:
        data_array = fn.read().split('\n')
    dataset = []
    dataset_txt['state'] = 'standard'
    dataset_txt.delete('1.0', 'end')

    for i in range(len(data_array)):
        dataset.append(f'{i + 1}.{data_array[i]}')
    dataset_txt.insert('1.0', '\n'.join(dataset))
    dataset_txt['state'] = 'disabled'

def RecordActivation():
    global cache_container, cache_capacity, alg_flag, iteratiton

    try:
        record_number = int(element_number_txt.get())
        filecontent = dataset_txt.get('1.0', 'end').split('\n')
        if record_number < len(filecontent):
            updated_cache_entry = filecontent[record_number - 1].split(' ')[1]
            iteration += 1
            if not cache_container or len(cache_container) < cache_capacity:
                incl_in_cache = False

                for elements in cache_container:
                    if updated_cache_entry == elements[0]:
                        incl_in_cache = True
                        elements[1] = iteration
                        elements[2] += 1
                        break
                if not incl_in_cache:
                    cache_container.append([updated_cache_entry, iteration, 1])
            else:
                incl_in_cache = False
                for elements in cache_container:
                    if updated_cache_entry == elements[0]:
                        incl_in_cache = True
                        elements[1] = iteration
                        elements[2] += 1

                if not incl_in_cache:
                    if alg_flag == 'LRU':
                        min_iteration = cache_container[0][1]
                        min_iteration_id = j
                    cache_container.pop(min_iteration_id)
                    cache_container.append([updated_cache_entry, iteration, 1])

    


