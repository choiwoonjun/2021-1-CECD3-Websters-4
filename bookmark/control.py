import cv2

from func.data import getData
from func.image import drawContour, showImage
from setting import cap

term = 30  # 프레임 간격

if cap.isOpened():

    while True:  # 무한 루프
        ret, image = cap.read()  # 두 개의 값을 반환하므로 두 변수 지정
        if not ret:  # 새로운 프레임을 못받아 왔을 때 braek
            break
        nowFrame = cap.get(cv2.CAP_PROP_POS_FRAMES)

        data = getData(image)
        drawContour(image, data)
        showImage(image, "a")
        print("Now Frame Number : ",nowFrame)

        key = cv2.waitKey(0);
        if key == 110:  # n 이전
            nowFrame = nowFrame - term - 1
        elif key == 109:  # m 다음
            nowFrame = nowFrame + term - 1
        else:
            break

        cap.set(cv2.CAP_PROP_POS_FRAMES, nowFrame)

    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()

else:
    print('cannot open the file')
