package API.POJO.steps;

import API.POJO.BodyArgs;
import API.POJO.BodyResult;
import API.POJO.bodies.projects.ProjectId;
import API.POJO.bodies.tasks.CreateTask;
import API.POJO.bodies.tasks.TaskId;
import API.POJO.bodies.tasks.TaskProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

import static API.POJO.methods.Tasks.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class TaskApiSteps extends BaseApiSteps{
    public String createTask(String taskTitle, Integer projectId,
                             String colorId, Integer columnId, Integer ownerId, Integer creatorId,
                             String dateDue, String description, Integer categoryId, Integer score,
                             Integer swimlaneId, Integer priority, Integer recurrenceStatus,
                             Integer recurrenceTrigger, Integer recurrenceFactor, Integer recurrenceTimeframe,
                             Integer recurrenceBasedate, String reference, List<String> tags,
                             String dateStarted){
        CreateTask args = CreateTask.builder()
                .title(taskTitle)
                .project_id(projectId)
                .color_id(colorId)
                .column_id(columnId)
                .owner_id(ownerId)
                .creator_id(creatorId)
                .date_due(dateDue)
                .description(description)
                .category_id(categoryId)
                .score(score)
                .swimlane_id(swimlaneId)
                .priority(priority)
                .recurrence_status(recurrenceStatus)
                .recurrence_trigger(recurrenceTrigger)
                .recurrence_factor(recurrenceFactor)
                .recurrence_timeframe(recurrenceTimeframe)
                .recurrence_basedate(recurrenceBasedate)
                .reference(reference)
                .tags(tags)
                .date_started(dateStarted)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }
    public String createTaskRequiredParams(String taskTitle, Integer projectId){
        CreateTask args = CreateTask.builder()
                .title(taskTitle)
                .project_id(projectId)
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_TASK)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public boolean removeTask(Integer taskId) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new TaskId(taskId))
                .method(REMOVE_TASK)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public BodyResult<TaskProperties> getTaskProperties (Integer taskId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new TaskId(taskId))
                .method(GET_TASK)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<BodyResult<TaskProperties>>() {});
    }
}
