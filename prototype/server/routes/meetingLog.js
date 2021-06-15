const express=require('express')
const router=express.Router();
const {getTranscriptionJob,editTranscription}=require('../lib/transcribe')
router.post('/:meetingId',(req,res)=>{
    const meetingId=req.params.meetingId

})
router.get('/:meetingId',async (req,res)=>{
    const meetingId=req.params.meetingId
    const paths=await getTranscriptionJob('111')
    editTranscription(paths)
    res.json({})
})

module.exports=router
