package UoBToolchainGroup.DistributedToolchainIntegration;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import UoBToolchainGroup.DistributedToolchainIntegration.serviceTests.ProjectServiceTest;
import UoBToolchainGroup.DistributedToolchainIntegration.serviceTests.UserServiceTest;

@Suite
@SuiteDisplayName("Service Test Suite")
@SelectClasses({UserServiceTest.class, ProjectServiceTest.class})
public class ServiceTestSuite {
    
}
