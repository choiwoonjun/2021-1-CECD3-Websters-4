const {MeetingLog}=require('../models/meetingLog')
const fs = require('fs-extra');
const path = require('path');


const saveMeetingLog=(meetingId,transPath)=>{
    const transcriptJson = JSON.parse(fs.readFileSync(transPath, 'utf8'))
    const imagePath=path.resolve(transPath,'../images/images.json')
    console.log(imagePath)
    const imageJson = JSON.parse(fs.readFileSync(imagePath, 'utf8'))
    
    const meetingLog=new MeetingLog({id:meetingId,images:imageJson,transcripts:transcriptJson.meeting_log})
    meetingLog.save()

}


module.exports={saveMeetingLog}