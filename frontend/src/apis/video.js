import axios from './axios'

const videoAPI={
    get:(data)=>{
        const url='/problem'
        return axios.get(url,data)
    },
    upload:(video)=>{
        const url='/api/v1/video'
        axios.post(url,formData, config)
    }
    
}

export default videoAPI