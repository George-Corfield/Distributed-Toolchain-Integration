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
        userId: ObjectId("6628696e117fd47726a8116b"),
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
        projectId: ObjectId("6628696e117fd47726a8115b"),
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Part"
    },
    {
        _id : ObjectId("6728696e117fd47726a8115b"),
        partName : "test-part-2",
        partDescription: "test",
        optimisationParams : {
            _id : ObjectId("6828696e117fd47726a8115b"),
            iterations : 100,
            modules: [ObjectId("6928696e117fd47726a8115a"),ObjectId("6928696e117fd47726a8115b")],
            maximising: true
        },
        projectId: ObjectId("6628696e117fd47726a8115b"),
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Part"
    },
    {
        _id : ObjectId("6728696e117fd47726a8115c"),
        partName : "test-part-3",
        partDescription: "test",
        optimisationParams : {
            _id : ObjectId("6828696e117fd47726a8115c"),
            iterations : 100,
            modules: [ObjectId("6928696e117fd47726a8115a"),ObjectId("6928696e117fd47726a8115b")],
            maximising: true
        },
        projectId: ObjectId("6628696e117fd47726a8115d"),
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Part"
    }
])

db.Variables.insert([
    {
        _id : ObjectId("6928696e117fd47726a8115a"),
        partId: ObjectId("6728696e117fd47726a8115a"),
        variableName : "test-variable-1",
        initVal : 10.00,
        lowBound : 5.00,
        upBound : 15.00,
        result : false,
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Variable"
    },
    {
        _id : ObjectId("6928696e117fd47726a8115b"),
        partId: ObjectId("6728696e117fd47726a8115a"),
        variableName : "test-variable-2",
        initVal : 10.00,
        lowBound : 5.00,
        upBound : 15.00,
        result : false,
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Variable"
    },
    {
        _id : ObjectId("6928696e117fd47726a8115c"),
        partId: ObjectId("6728696e117fd47726a8115b"),
        variableName : "test-variable-3",
        initVal : 10.00,
        lowBound : 5.00,
        upBound : 15.00,
        result : false,
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.Variable"
    }
])

db.Files.insert([
    {
        _id : ObjectId("6428696e117fd47726a8115a"),
        fileName : "test-modfile-1",
        contentType : "text/x-python-script",
        data : [],
        userId : ObjectId("6628696e117fd47726a8116e"),
        publicFile : true,
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile"
    },
    {
        _id : ObjectId("6428696e117fd47726a8115b"),
        fileName : "test-modfile-2",
        contentType : "text/x-python-script",
        data : [],
        userId : ObjectId("6628696e117fd47726a8116b"),
        publicFile : false,
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile"
    },
    {
        _id : ObjectId("6428696e117fd47726a8115c"),
        fileName : "test-varfile-1",
        contentType : "application/json",
        data : [],
        _class : "UoBToolchainGroup.DistributedToolchainIntegration.model.VariablesFile"
    }
])
