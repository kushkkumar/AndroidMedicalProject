const express=require('express');
const app=express();
app.use(express.json())

const { Client } = require('pg');

const client = new Client({
    connectionString: 'postgres://nypaphcngqlcej:0700b95abce51e299d7abd5782c69eb30c37e7d28352aedc11bc1da6e755abdc@ec2-54-80-184-43.compute-1.amazonaws.com:5432/d6ed2cpepa4quc',
    ssl: true,
  });
  client.connect();



app.get('/',(req,response)=>{

    response.send("hello")
  
})





app.get('/table/temppatient',(request,response)=>{
    client.query('SELECT * FROM temppatient;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})

app.get('/table/permanentpatient',(request,response)=>{
    client.query('SELECT * FROM temppatient;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})


app.get('/table/doctordetails',(request,response)=>{
    res.send("table1")
})

app.get('/table/client',(request,response)=>{
    res.send("table1")
})


app.get('/table/permanentdiseasedetails',(request,response)=>{
    res.send("table1")
})



app.get('/table/permanentslot',(request,response)=>{
    res.send("table1")
})






app.listen(process.env.PORT||8080,()=>console.log("its working "))
