package io.mogtabaintellegence.exceptions;

/**
 * Created by Interface 3 on 2/2/2020.
 */
public class ProjectIdExceptionResponse {
    private  String projectIdentifier;

    public ProjectIdExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
