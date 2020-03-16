const express=require('express');
const app=express();
app.use(express.json())


const { Client } = require('pg');

const client = new Client({
    connectionString: 'postgres://nypaphcngqlcej:0700b95abce51e299d7abd5782c69eb30c37e7d28352aedc11bc1da6e755abdc@ec2-54-80-184-43.compute-1.amazonaws.com:5432/d6ed2cpepa4quc',
    ssl: true,
  });
  client.connect();






app.post('/temppatientRegistration',(request,response)=>{
    tempPatientRegistration(request,response);
})

app.post('/permanentRegistraion',(request,response)=>{
    permanentRegistraion(request,response);
})


function permanentRegistraion(request,response){
    const name=request.body.name;
    const email=request.body.email;
    const phone=request.body.phone;
    const bod=request.body.bod;
    const address=request.body.address;
    const gender=request.body.gender;



client.query("INSERT INTO permanentpatient (ppid,name,bdate,gender,phoneno,email,address) VALUES ('pt01','"+name+"','"+bod+"','"+gender+"','"+phone+"','"+email+"','"+address+"');",(err,res)=>{
    if(err) response.status(401).send();
    response.status(201).send();
    console.log("1 data inserted"+res);
    
})

}




function tempPatientRegistration(request,response){
    const name=request.body.name;
    const email=request.body.email;
    const phone=request.body.phone;
    const bod=request.body.bod;
    const address=request.body.address;
    const gender=request.body.gender;



client.query("INSERT INTO temppatient (tpid,name,bdate,gender,phoneno,email,address) VALUES ('tp16','"+name+"','"+bod+"','"+gender+"','"+phone+"','"+email+"','"+address+"');",(err,res)=>{
    if(err) response.status(400).send();
    response.status(200).send();
    console.log("1 data inserted"+res);
    
})

}








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
    client.query('SELECT * FROM permanentpatient;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})


app.get('/table/doctordetails',(request,response)=>{
    client.query('SELECT * FROM doctordetails;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})

app.get('/table/client',(request,response)=>{
    client.query('SELECT * FROM client;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})


app.get('/table/permanentdiseasedetails',(request,response)=>{
    client.query('SELECT * FROM permanentdiseasedetails;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})



app.get('/table/permanentslot',(request,response)=>{
    client.query('SELECT * FROM permanentslot;', (err, res) => {
        if (err) throw err;
       response.send(res);
        client.end();
      });
})






app.listen(process.env.PORT||9050,()=>console.log("its working "))
