const express=require('express')
const router=express.Router();
const {getTranscriptionJob,editTranscription}=require('../lib/transcribe')
const {saveMeetingLog}=require('../lib/meetingLog')
const {MeetingLog}=require('../models/meetingLog')

router.get('/save',async (req,res)=>{
    saveMeetingLog()
})
router.get('/image',async (req,res)=>{
    const params=new URLSearchParams(req.query)
    const imagePath=params.get('imagePath')
    res.sendFile(imagePath)
}) 
router.post('/:meetingId',async (req,res)=>{
    const meetingId=req.params.meetingId
    const paths=await getTranscriptionJob('111')
    editTranscription(paths)
    saveMeetingLog(meetingId,paths.editTranscPath)
    res.json({})
})
router.get('/:meetingId',async (req,res)=>{
    const meetingId=req.params.meetingId
    const meetingLog=await MeetingLog.findOne({id:meetingId})
    res.json(meetingLog)
})  



module.exports=router
