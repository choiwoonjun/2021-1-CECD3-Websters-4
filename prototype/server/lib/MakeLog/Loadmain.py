from Load import LoadClass
from Search import SearchClass
import sys

def main(argv):
    lc = LoadClass(argv[1],argv[2])
    speech = lc.getspeech()
    start_time = lc.gettime()
    #lc.prtlog()

if __name__ == "__main__":
    main(sys.argv)