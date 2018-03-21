mvn clean package && docker build -t psdating/api . && docker push psdating/api && kubectl delete deployment api-deployment && kubectl apply -f kubernetes/api-deployment.yaml
