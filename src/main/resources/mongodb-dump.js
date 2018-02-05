db.createCollection("com.itv.test.web.repository.dto.Product");
db.getCollection("com.itv.test.web.repository.dto.Product").createIndex({"productId": 1});

db.createCollection("com.itv.test.web.repository.dto.Promotion");
db.getCollection("com.itv.test.web.repository.dto.Promotion").createIndex({"productId": 1});

db.getCollection('com.itv.test.web.repository.dto.Product').insert({
    "productId" : "A",
    "price" : 0.50,
    "description" : "Product A"
});
db.getCollection('com.itv.test.web.repository.dto.Product').insert({
    "productId" : "B",
    "price" : 0.30,
    "description" : "Product B"
});
db.getCollection('com.itv.test.web.repository.dto.Product').insert({
    "productId" : "C",
    "price" : 0.20,
    "description" : "Product C"
});
db.getCollection('com.itv.test.web.repository.dto.Product').insert({
    "productId" : "D",
    "price" : 0.15,
    "description" : "Product D"
});

db.getCollection('com.itv.test.web.repository.dto.Promotion').insert({
    "productId" : "A",
    "quantity" : 3,
    "price" : 1.30
});
db.getCollection('com.itv.test.web.repository.dto.Promotion').insert({
    "productId" : "B",
    "quantity" : 2,
    "price" : 0.45
});