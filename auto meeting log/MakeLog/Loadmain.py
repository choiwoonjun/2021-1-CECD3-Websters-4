from Load import LoadClass
from Search import SearchClass


def main():
    lc = LoadClass()
    speech = lc.getspeech()
    start_time = lc.gettime()
    while 1:
        choice = int(input("Insert Order (0 : exit, 1 : print Meeting log, 2 : Search Sentence, 3 : Check json) >> "))
        if choice == 0:
            break
        elif choice == 1:
            lc.prtlog()
        elif choice == 2:
            sc = SearchClass(speech, start_time)
        elif choice == 3:
            lc.opjson()

if __name__ == "__main__":
    main()