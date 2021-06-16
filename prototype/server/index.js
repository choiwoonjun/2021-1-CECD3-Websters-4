const express=require('express')
const app=express()
const bodyParser = require('body-parser');
const port=5000
app.use(bodyParser.urlencoded({ extended: true }))
app.use(express.json())
const path=require('path')
require('dotenv').config({ path: path.join(__dirname, './.env') })
const mongoose=require('mongoose')
mongoose.connect(process.env.MongoURI,{ useNewUrlParser: true,useUnifiedTopology: true,useCreateIndex:true,useFindAndModify:false})

app.use((req,res,next)=>{
    res.setHeader('Access-Control-Allow-Origin', process.env.ClientDomain);
    // res.setHeader('Access-Control-Allow-Headers','content-type')
    // res.header("Access-Control-Allow-Credentials", true);
    next()
})

app.get('/',(req,res)=>{
    console.log('get')
    res.send('hihi')
})
app.listen(port,()=>{
    console.log(`listening on ${port}`)
})
app.use('/meetingLog',require('./routes/meetingLog'))
