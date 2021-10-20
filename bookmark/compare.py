# frame1 과 frame2 비교
from func.image import getImage
from func.util import *

frame1 = 2370
frame2 = 2400

image1 = getImage(frame1)
image2 = getImage(frame2)

frame1 = Frame(frame1)
frame2 = Frame(frame2)

frame1.show("1")
frame2.show("2")

result_frame = frame2.getResultFrame(frame1)
result_frame.show("result")

cv2.waitKey(0)





