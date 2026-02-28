A spring boot project that interacts with etcd

Bring up the app and etcd server with:

`docker compose up --build`


```shell
# put a key/value pair
curl -X POST \                       
-H "Content-Type: application/json" \
-d '{"key": "phone", "value": "pixel"}' \
http://localhost:9220/put

# get value for key
curl http://localhost:9220/get/phone

# watch value for key - logs new/previous values when changed with put or delete
curl http://localhost:9220/watch/phone

# delete key/value pair for given key
curl http://localhost:9220/delete/phone
```