# frame1 과 frame2 비교
from func.image import getImage
from func.util import *

frame = 1530
prevFrame = frame-fps

image1 = getImage(frame)
image2 = getImage(prevFrame)

frame = Frame(frame)
prevFrame = Frame(prevFrame)

frame.show("now")
prevFrame.show("prev")

frame.setMismatchContours(prevFrame)
frame.showMismatch("mismatch")

cv2.waitKey(0)





