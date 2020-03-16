const express=require('express');
const app=express();
app.use(express.json())


const { Client } = require('pg');

const client = new Client({
    connectionString: 'postgres://nypaphcngqlcej:0700b95abce51e299d7abd5782c69eb30c37e7d28352aedc11bc1da6e755abdc@ec2-54-80-184-43.compute-1.amazonaws.com:5432/d6ed2cpepa4quc',
    ssl: true,
  });
  client.connect();


var counttp=1;
client.query("SELECT * FROM temppatient",(err,res)=>{
    counttp=counttp+res.rowCount;
   // console.log(counttp)
})
//console.log(counttp);


//all the request from the android that are sent

app.post('/temppatientRegistration',(request,response)=>{
    tempPatientRegistration(request,response);
})

app.post('/permanentRegistraion',(request,response)=>{
    permanentRegistraion(request,response);
})


app.post('/bookSlotPermanent',(request,response)=>{
    bookSlotPermanent(request,response);
})

app.post('/confirmPermanent',(request,response)=>{
    confirmPermanent(request,response);
})

//the funtions that are needed to send back the information for the android


function confirmPermanent(request,response){
    client.query("SELECT * FROM permanentpatient",(err,res)=>{
        var c="pt";
        c=c+(res.rowCount.toString());



        client.query("SELECT * FROM permanentpatient WHERE ppid='"+c+"';",(err,result)=>{
            if(err) throw err
    
            if(result.rowCount==0){
                response.status(410).send();
            }
            else{
    
            
            response.status(210).send(JSON.stringify(result.rows[0]));
           
        }
        
        })
        

    })

}

function bookSlotPermanent(request,response){
    const ppid=request.body.ppid;
   

    client.query("SELECT * FROM permanentpatient WHERE ppid='"+ppid+"';",(err,result)=>{
        if(err) throw err

        if(result.rowCount==0){
            response.status(404).send();
        }
        else{

        
        response.status(202).send(JSON.stringify(result.rows[0]));
       
    }
    
    })
}

function permanentRegistraion(request,response){
    const name=request.body.name;
    const email=request.body.email;
    const phone=request.body.phone;
    const bod=request.body.bod;
    const address=request.body.address;
    const gender=request.body.gender;

    client.query("SELECT * FROM permanentpatient",(err,res)=>{
        var d="pt";
        d=d+((res.rowCount+1).toString());
        console.log(d);

client.query("INSERT INTO permanentpatient (ppid,name,bdate,gender,phoneno,email,address) VALUES ('"+d+"','"+name+"','"+bod+"','"+gender+"','"+phone+"','"+email+"','"+address+"');",(err,result)=>{
    if(err) response.status(401).send();
    response.status(201).send();
    console.log("1 data inserted"+result);
    
})
})

}

function tempPatientRegistration(request,response){
    const name=request.body.name;
    const email=request.body.email;
    const phone=request.body.phone;
    const bod=request.body.bod;
    const address=request.body.address;
    const gender=request.body.gender;

    client.query("SELECT * FROM temppatient",(err,res)=>{
        var e="tp";
        e=e+((res.rowCount+1).toString());
        console.log(e);


client.query("INSERT INTO temppatient (tpid,name,bdate,gender,phoneno,email,address) VALUES ('"+e+"','"+name+"','"+bod+"','"+gender+"','"+phone+"','"+email+"','"+address+"');",(err,result)=>{
    if(err) response.status(400).send();
    response.status(200).send();
    console.log("1 data inserted"+result);
    
})

    })

}

app.get('/',(req,response)=>{


    response.send("hello")
  
})

app.get('/table/temppatient',(request,response)=>{
    client.query('SELECT * FROM temppatient;', (err, res) => {
        if (err) throw err;
       response.send(res.rows);
        client.end();
      });
})

app.get('/table/permanentpatients',(request,response)=>{
    client.query('SELECT * FROM permanentpatient;', (err, res) => {
        if (err) throw err;
       response.send(res.rows);
        client.end();
      });
})

app.get('/table/doctordetails',(request,response)=>{
    client.query('SELECT * FROM doctordetails;', (err, res) => {
        if (err) throw err;
       response.send(res.rows);
        client.end();
      });
})

app.get('/table/client',(request,response)=>{
    client.query('SELECT * FROM client;', (err, res) => {
        if (err) throw err;
       response.send(res.rows);
        client.end();
      });
})

app.get('/table/permanentdiseasedetails',(request,response)=>{
    client.query('SELECT * FROM permanentdiseasedetails;', (err, res) => {
        if (err) throw err;
       response.send(res.rows);
        client.end();
      });
})

app.get('/table/permanentslot',(request,response)=>{
    client.query('SELECT * FROM permanentslot;', (err, res) => {
        if (err) throw err;
       response.send(res.rows);
        client.end();
      });
})

app.listen(process.env.PORT||6005,()=>console.log("its working "))
