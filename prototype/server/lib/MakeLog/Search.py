class SearchClass:

    def __init__(self, speech, start_time):
        word = input("Insert Finding Word >> ")
        check = 0
        for i in range(0, len(speech)):
            if word in speech[i]:
                check = 1
                print(start_time[i] + " " + speech[i])
        if check == 0:
            print("Can not Find \"" + word + "\"")