import cv2

from func.util import getFrameUtil
from setting import cap, frameIndexs

if cap.isOpened():

    frameNum = len(frameIndexs)
    idx = 1
    frameUtil = getFrameUtil()
    while True:  # 무한 루프

        nowFrame = frameIndexs[idx]
        print("Now Frame Number : ", nowFrame)
        cap.set(cv2.CAP_PROP_POS_FRAMES, frameIndexs[idx])
        ret, image = cap.read()  # 두 개의 값을 반환하므로 두 변수 지정
        if not ret:  # 새로운 프레임을 못받아 왔을 때 braek
            break

        nowFrame = frameUtil.frameList[idx]
        prevFrame = frameUtil.frameList[idx-1]

        nowFrame.show("now")
        prevFrame.show("prev")

        resultFrame = nowFrame.getResultFrame(prevFrame)
        resultFrame.show("result")
        key = cv2.waitKey(0);


        if key == 110 and idx>0:  # n 이전
            idx-=1
        elif key == 109 and idx<frameNum-1:  # m 다음
            idx+=1
        elif key == 106 and idx+10<frameNum-1:  # j 앞으로 점프
            idx+=10
        elif key == 104 and idx-10>0:  # h 뒤로 점프
            idx-=10
        else:
            break


    cap.release()  # 사용한 자원 해제
    cv2.destroyAllWindows()

else:
    print('cannot open the file')
