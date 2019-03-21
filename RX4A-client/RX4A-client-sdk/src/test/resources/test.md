
## query TraceInStock
peer chaincode query -o orderer0.example.com:7050 -C mychannel -n TraceInStock -c '{"Args":["getTraceInStockById","e6d91452-8bda-4b08-965c-ce8bb19126fb"]}'

peer chaincode query -o orderer0.example.com:7050 -C mychannel -n TraceInStock -c '{"Args":["getTraceInStockByTxId","ace2542d72965e526848c0da4a529ba7a3a2e44932fe49bb225da573e3c85d33"]}'