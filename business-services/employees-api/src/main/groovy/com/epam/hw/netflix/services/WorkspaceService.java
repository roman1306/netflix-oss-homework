package com.epam.hw.netflix.services;

import static com.epam.hw.netflix.domain.OSFamily.LINUX;

import com.epam.hw.netflix.api.WorkspaceAPI;
import com.epam.hw.netflix.domain.Workspace;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

  @Autowired
  private WorkspaceAPI workspaceAPI;

  @HystrixCommand(fallbackMethod = "getDefaultWorkspace")
  public Workspace getWorkspaceById(String id) {
    return workspaceAPI.getWorkspaceById(id);
  }

  private Workspace getDefaultWorkspace(String id) {
    return new Workspace("0000000", 0, 0, "default number", LINUX);
  }
}
