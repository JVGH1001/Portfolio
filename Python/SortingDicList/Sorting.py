file = input()

with open(file) as f:
    data = f.readlines()
    dict_info = {}

for i in range(0, len(data)-1, 2):
    season = int(data[i].strip())
    name = data[i+1].strip()
    if season in dict_info:
        dict_info[season].append(name)
    else:
        dict_info[season] = [name]


keys = list(dict_info.keys())
keys.sort()

with open('output_keys.txt', 'w') as f:
    for key in keys:
        names = '; '.join(name for name in dict_info[key])
        f.write(str(key) + ': ' + names + "\n")

names = []

for item in dict_info:
    for name in dict_info[item]:
        names.append(name)

names.sort()

with open('output_titles.txt', 'w') as f:
    for name in names:
        f.write(name+'\n')
