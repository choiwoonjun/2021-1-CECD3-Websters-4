from classes.FrameUtil import FrameUtil
from func.util import getFrameUtilCache


# frameUtil = FrameUtil(None)
frameUtil = getFrameUtilCache()
boomarks = frameUtil.getBoomarks()
print(boomarks)