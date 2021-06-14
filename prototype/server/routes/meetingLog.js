const express=require('express')
const router=express.Router();
const {getTranscriptionJob,editTranscription}=require('../lib/transcribe')
router.post('/:meetingId',(req,res)=>{
    const meetingId=req.params.meetingId

})
router.get('/:meetingId',async (req,res)=>{
    const meetingId=req.params.meetingId
    const transcription=await getTranscriptionJob('111')
    editTranscription(transcription)
    res.send(200)
})

module.exports=router
