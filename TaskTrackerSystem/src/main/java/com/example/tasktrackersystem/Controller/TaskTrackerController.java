package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.ApiResponse.ApiResponse;
import com.example.tasktrackersystem.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/tasktrackersystem")
public class TaskTrackerController {

    ArrayList<TaskTracker> taskTrackers = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse createTaks(@RequestBody TaskTracker tasktracker ){
        taskTrackers.add(tasktracker);
        return new ApiResponse("ok");
    }
    @GetMapping("/get")
    public ArrayList<TaskTracker> displayAlltasks() {

        return taskTrackers;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@RequestBody TaskTracker tasktracker,@PathVariable int index ) {

        taskTrackers.set(index,tasktracker);
        return new ApiResponse("ok");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        return new ApiResponse("ok");
    }

    @PutMapping("/status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (taskTrackers.get(index).getStatus().equals("not done")) {

            taskTrackers.get(index).setStatus("done");
        }else{
            taskTrackers.get(index).setStatus("not done");
        }
        return new ApiResponse("ok");
    }

    @GetMapping("/search/{title}")
    public TaskTracker Search(@PathVariable String title) {
        //int size = taskTrackers.size();
         TaskTracker taskTracker = new TaskTracker("","","","");
        for (TaskTracker task :taskTrackers) {
           if(task.getTitle().equalsIgnoreCase(title) ){
               taskTracker = task;
              break;
           }

        }
        return taskTracker ;
    }
}
