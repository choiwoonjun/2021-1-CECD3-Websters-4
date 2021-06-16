const { TranscribeClient,GetTranscriptionJobCommand } = require("@aws-sdk/client-transcribe");
const REGION = "ap-northeast-2";
const transcribeClient = new TranscribeClient({ region: REGION });
const axios=require('axios')
const spawn = require("child_process").spawn;
const path = require('path');
const fs = require('fs-extra');


const getTranscriptionJob=(TranscriptionJobName)=>{
    return new Promise(async (resolve,reject)=>{
        try {
            const transcriptionJob = await transcribeClient.send(new GetTranscriptionJobCommand({TranscriptionJobName}));
            const TranscriptFileUri=transcriptionJob.TranscriptionJob.Transcript.TranscriptFileUri
            const response=await axios.get(TranscriptFileUri)
            const transcription=JSON.stringify(response.data)
            const dirPath = path.resolve("./files", `${TranscriptionJobName}`);
            fs.mkdirSync(dirPath, {recursive: true});
            const rawTranscPath=path.resolve(dirPath,'raw.json')
            fs.createFileSync(rawTranscPath);
            //fs.writeFileSync(rawTranscPath, Buffer.from(transcription));
            const editTranscPath=path.resolve(dirPath,'edit.json')
            resolve({rawTranscPath,editTranscPath})
        } catch (error) {
            console.log("Error", error);
            reject()
        }
    })
}

const editTranscription=(paths)=>{
    const rawTranscPath=paths.rawTranscPath
    const editTranscPath=paths.editTranscPath
    console.log('-----------')
    console.log(rawTranscPath)
    const pythonProcess = spawn('python',["./lib/MakeLog/Loadmain.py",rawTranscPath,editTranscPath]);
    pythonProcess.stdout.setEncoding('utf8')
    pythonProcess.stderr.setEncoding('utf8')
    pythonProcess.stdout.on('data', (data) => {
        console.log(data)
    });
    pythonProcess.stderr.on('data', (data) => {
        console.log(data)
    });
}




module.exports={ getTranscriptionJob,editTranscription };
