const  grpc =  require('grpc');
const protoloader =  require('@grpc/proto-loader')

const packageDef =  protoloader.loadSync('proto/bank-service.proto')
const protoDesc =  grpc.loadPackageDefinition(packageDef);

const client  =  new protoDesc.BankService('localhost:6565', grpc.credentials.createInsecure())

client.getBalance({accountNumber: 7}, (err, balance) => {

    if(err){
        console.error('Something went wrong')
    }else{
        console.log('Received : ' +  balance.amount);
    }
});