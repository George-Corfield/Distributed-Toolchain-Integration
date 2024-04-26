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

db.Parts.insert([
    {
        _id : ObjectId("6728696e117fd47726a8115a"),
        partName : "test-part-1",
        partDescription: "test",
        optimisationParams : {
            _id : ObjectId("6828696e117fd47726a8115a"),
            iterations : 100,
            modules: [ObjectId("6928696e117fd47726a8115a"),ObjectId("6928696e117fd47726a8115b")],
            maximising: true
        },
        projectId: ObjectId("6628696e117fd47726a8115b")
    },
    {
        _id : ObjectId("6728696e117fd47726a8115b"),
        partName : "test-part-1",
        partDescription: "test",
        optimisationParams : {
            _id : ObjectId("6828696e117fd47726a8115b"),
            iterations : 100,
            modules: [ObjectId("6928696e117fd47726a8115a"),ObjectId("6928696e117fd47726a8115b")],
            maximising: true
        },
        projectId: ObjectId("6628696e117fd47726a8115b")
    },
    {
        _id : ObjectId("6728696e117fd47726a8115c"),
        partName : "test-part-1",
        partDescription: "test",
        optimisationParams : {
            _id : ObjectId("6828696e117fd47726a8115c"),
            iterations : 100,
            modules: [ObjectId("6928696e117fd47726a8115a"),ObjectId("6928696e117fd47726a8115b")],
            maximising: true
        },
        projectId: ObjectId("6628696e117fd47726a8115d")
    }
])