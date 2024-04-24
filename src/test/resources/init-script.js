db = db.getSiblingDB('service_test');

db.users.insert([
    {
        _id : ObjectId("6628696e117fd47726a8116e"),
        username : "John",
        password : [],
        email : "John@test.com",
        role : 10,
        salt : [], 
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.User"
    } , 
    {
        _id : ObjectId("6628696e117fd47726a8116b"),
        username : "Jack",
        password : [],
        email : "Jack@test.com",
        role : 10,
        salt : [], 
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.User"
    },
    {
        _id : ObjectId("6628696e117fd47726a8116c"),
        username : "Jason",
        password : [],
        email : "Jason@test.com",
        role : 10,
        salt : [], 
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.User"
    }      
])