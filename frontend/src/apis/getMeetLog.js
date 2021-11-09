import axios from './axios'

const getMeetingLog=(meetingID)=>{
    return new Promise(async (resolve,reject)=>{
        const res=await axios.get(`/meetingLog/${meetingID}`)
        resolve(res.data)
    })
}

export default getMeetingLog