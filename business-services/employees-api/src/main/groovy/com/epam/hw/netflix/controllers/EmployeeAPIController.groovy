package com.epam.hw.netflix.controllers

import com.epam.hw.netflix.api.WorkspaceAPI
import com.epam.hw.netflix.services.EmployeeService
import com.epam.hw.netflix.services.WorkspaceService
import com.netflix.hystrix.HystrixCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeAPIController {

    @Autowired
    EmployeeService employeeService

    @Autowired
    WorkspaceService workspaceService

    @RequestMapping("/{id}")
    def describeEmployee(@PathVariable("id") String id) {
        def employee = employeeService.findEmployee(id)

        [
                id       : employee.id,
                firstName: employee.firstName,
                lastName : employee.lastName,
                email    : employee.email,
                workspace: workspaceService.getWorkspaceById(id) // null? Nope. Let's request exact workspace by employee.workspaceId from workspaces-api. How? With feign client maybe?
        ]
    }
}
