const { TranscribeClient,GetTranscriptionJobCommand } = require("@aws-sdk/client-transcribe");
const REGION = "ap-northeast-2";
const transcribeClient = new TranscribeClient({ region: REGION });
const axios=require('axios')
const spawn = require("child_process").spawn;


const getTranscriptionJob=(TranscriptionJobName)=>{
    return new Promise(async (resolve,reject)=>{
        try {
            const transcriptionJob = await transcribeClient.send(new GetTranscriptionJobCommand({TranscriptionJobName}));
            const TranscriptFileUri=transcriptionJob.TranscriptionJob.Transcript.TranscriptFileUri
            const response=await axios.get(TranscriptFileUri)
            const transcription=response.data
            resolve(transcription)
        } catch (error) {
            console.log("Error", error);
            reject()
        }
    })
   
}

const editTranscription=(transcription)=>{
    const string=JSON.stringify(transcription)
    const pythonProcess = spawn('python',["./lib/test.py"]);
    pythonProcess.stdout.setEncoding('utf8')
    pythonProcess.stderr.setEncoding('utf8')
    pythonProcess.stdin.write(Buffer.from(string))
    pythonProcess.stdin.end()
    pythonProcess.stdout.on('data', (data) => {
        console.log(data)
    });
    pythonProcess.stderr.on('data', (data) => {
        console.log(data)
    });
}


module.exports={ getTranscriptionJob,editTranscription };
