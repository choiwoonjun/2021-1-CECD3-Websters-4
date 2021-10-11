import cv2

from func.util import showVideoInfo

filePath = "resource/test1.mp4"
cap = cv2.VideoCapture(filePath)

if cap.isOpened():
    showVideoInfo()
    nowFrame = cap.get(cv2.CAP_PROP_POS_FRAMES)

    while True:
        print("프레임 번호 입력")
        nowFrame = int(input())

        cap.set(cv2.CAP_PROP_POS_FRAMES, nowFrame)
        ret, image = cap.read()  # 두 개의 값을 반환하므로 두 변수 지정
        cv2.imshow("a",image)
        key = cv2.waitKey(0)

        if key == 27: # esc
            break

    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()

else:
    print('cannot open the file')
