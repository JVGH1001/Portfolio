import csv

word_list = []
user_input = input()
with open(user_input, 'r') as name_CSV:
    copy = csv.reader(name_CSV)
    for lines in copy:
        for w in lines:
            word_list.append(w)
            words_cnt = lines.count(w)
            print(w, words_cnt)
word_set = set(word_list)
