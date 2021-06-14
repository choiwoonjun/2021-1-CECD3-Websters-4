const express=require('express')
const app=express()
const bodyParser = require('body-parser');
const port=5000
app.use(bodyParser.urlencoded({ extended: true }))
app.use(express.json())

app.get('/',(req,res)=>{
    console.log('get')
    res.send('hihi')
})
app.listen(port,()=>{
    console.log(`listening on ${port}`)
})
app.use('/meetingLog',require('./routes/meetingLog'))
