db = db.getSiblingDB('service_test');

db.Users.insert([
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

db.Projects.insert([
    {
        _id : ObjectId("6628696e117fd47726a8115b"),
        projectName: "test-project-1",
        projectDescription:"test",
        userId: ObjectId("6628696e117fd47726a8116e"),
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Project"
    },
    {
        _id : ObjectId("6628696e117fd47726a8115d"),
        projectName: "test-project-2",
        projectDescription:"test",
        userId: ObjectId("6628696e117fd47726a8116e"),
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Project"
    },
    {
        _id : ObjectId("6628696e117fd47726a8115c"),
        projectName: "test-project-3",
        projectDescription:"test",
        userId: ObjectId("6628696e117fd47726a8116e"),
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Project"
    }
])