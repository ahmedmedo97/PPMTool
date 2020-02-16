package io.mogtabaintellegence.exceptions;

/**
 * Created by Interface 3 on 2/16/2020.
 */
public class ProjectNotFoundExceptionResponse {
    private  String ProjectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.ProjectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return ProjectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.ProjectNotFound = projectNotFound;
    }
}
