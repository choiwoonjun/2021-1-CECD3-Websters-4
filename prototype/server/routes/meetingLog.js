const express=require('express')
const router=express.Router();
const {getTranscriptionJob,editTranscription}=require('../lib/transcribe')
const {saveMeetingLog}=require('../lib/meetingLog')
router.post('/:meetingId',(req,res)=>{
    const meetingId=req.params.meetingId

})

router.get('/save',async (req,res)=>{
    saveMeetingLog()
})
router.get('/:meetingId',async (req,res)=>{
    const meetingId=req.params.meetingId
    const paths=await getTranscriptionJob('111')
    editTranscription(paths)
    saveMeetingLog(paths.editTranscPath)
    res.json({})
})




module.exports=router
