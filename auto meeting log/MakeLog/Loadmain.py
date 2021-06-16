from Load import LoadClass
from Search import SearchClass
from Load2 import LoadClass2


def main():
    lc = LoadClass()
    speech = lc.getspeech()
    start_time = lc.gettime()
    lc2= LoadClass2();
    while 1:
        choice = int(input("Insert Order (0 : exit, 1 : print Meeting log(1), 2 : print Meeting log(2), 3 : Search Sentence, 4 : Check json) >> "))
        if choice == 0:
            break
        elif choice == 1:
            lc.prtlog()
        elif choice == 2:
            lc2.prtlog()
        elif choice == 3:
            sc = SearchClass(speech, start_time)
        elif choice == 4:
            lc.opjson()

if __name__ == "__main__":
    main()