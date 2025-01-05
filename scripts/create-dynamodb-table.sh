#!/bin/bash

aws dynamodb create-table --table-name simulation --attribute-definitions AttributeName=id,AttributeType=S AttributeName=email,AttributeType=S --key-schema AttributeName=id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --global-secondary-indexes '[{"IndexName":"EmailIndex","KeySchema":[{"AttributeName":"email","KeyType":"HASH"}],"Projection":{"ProjectionType":"ALL"},"ProvisionedThroughput":{"ReadCapacityUnits":5,"WriteCapacityUnits":5}}]' --endpoint-url http://localhost:54000 --region us-east-1

echo "DynamoDB table '$TABLE_NAME' created successfully."