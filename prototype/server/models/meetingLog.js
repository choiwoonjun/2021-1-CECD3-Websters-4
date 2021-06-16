const mongoose=require('mongoose')
const transcriptsSchema=mongoose.Schema({
    time:{type:Number},
    speech:{type:String}
})
const imageSchema=mongoose.Schema({
    type:Object,
    path:{type:String},
    time:{type:Number}
})
const meetinglogSchema=mongoose.Schema({
    id:{
        type:String,
        required:true
    },
    images:{
        type:[imageSchema]
    },
    transcripts:{
        type:[transcriptsSchema]
    }
})

const MeetingLog=mongoose.model('MeetingLog',meetinglogSchema)

module.exports={MeetingLog}