package com.poli.paginator.repository;

import com.poli.paginator.models.ProcessModel;
import com.poli.paginator.models.TaskModel;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessRepository {

    private List<ProcessModel> processModelList;
    private List<Integer> spareMemory;
    private TaskModel task;


    public ProcessRepository (){

        this.task = new TaskModel(8);

        this.processModelList = new ArrayList() {{
            add(new ProcessModel(1, "word", 1, "inactive"));
            add(new ProcessModel(2, "excel", 2, "inactive"));
            add(new ProcessModel(3, "illustrator", 3, "inactive"));
            add(new ProcessModel(4, "eclipse", 4, "inactive"));
            add(new ProcessModel(5, "visual", 4, "inactive"));
        }};

    }

    public List<ProcessModel> getProcessList() {
        return this.processModelList;
    }

    public ProcessModel GetById(int id) {
        return processModelList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get();
    }

    public List<ProcessModel> GetByState(String state) {
        return processModelList.stream()
                .filter(p -> p.getState().equals(state))
                .collect(Collectors.toList());
    }

    public TaskModel remove(int id) {
        List<Integer> list = this.task.getPositions();
        int count = 0, totalUse = Collections.frequency(list, id);

        this.SetUsedMemory(id, list, count, totalUse);
        this.changeProcess(id, count, totalUse, "inactive", 0);
        this.watchPendingProcess("pending", "active");

        return this.task;
    }

    public TaskModel update(ProcessModel obj) {
        this.task.setListProcess(processModelList);
        String state = obj.getState();

        int used = this.task.getUsedMemory() + obj.getSizePage();

        if(task.getAvailibleMemory() < obj.getSizePage()) {
            state = "pending";
        } else if(state.equals("active")) {
            this.task.setUsedMemory(used);
            this.generateMemorySpace(obj.getId(), obj.getSizePage());
        }

        obj.setState(state);

        this.processModelList.remove(processModelList.stream()
                .filter(p -> p.getId() == obj.getId())
                .findFirst()
                .get()
        );
        this.processModelList.add(obj);

        return task;
    }


    private void generateMemorySpace(int process_id, int sizePage) {

        List<Integer> list = this.task.getPositions();
        int savedInMemory = sizePage;

        for(Integer item : list) {

            if (item.equals(0) && savedInMemory > 0) {
                int index = list.indexOf(item);
                list.set(index, process_id);
                savedInMemory -= 1;

                if (savedInMemory == 0){
                    break;
                }
            }
        }

        this.task.setPositions(list);
    }

    private void SetUsedMemory(int id, List<Integer> list, int count, int totalUse) {

        ListIterator<Integer> iterator = list.listIterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();

            if (next.equals(id)) {
                count++;
                iterator.set(0);

                if(totalUse == count){
                    break;
                }
            }
        }
        this.task.setUsedMemory(this.task.getUsedMemory() - totalUse);
    }

    private void changeProcess(int id, int count, int totalUse, String state, int siZeProcess){
        List<ProcessModel> processList =  this.task.getListProcess();
        ProcessModel match = processList.stream().filter((ProcessModel t) -> t.getId() == id).findFirst().get();

        ListIterator<ProcessModel> iterate = processList.listIterator();

        while (iterate.hasNext()) {
            ProcessModel next = iterate.next();

            if (next.equals(match)) {
                match.setState(state);
                iterate.set(match);

                if(totalUse == count){
                    break;
                }
            }
        }
    }

    private void watchPendingProcess(String oldState, String newState) {

        List<ProcessModel> pendingList = this.task.getListProcess()
                .stream()
                .filter((ProcessModel t) -> t.getState().equals(oldState))
                .collect(Collectors.toList());

        ListIterator<ProcessModel> iterate = pendingList.listIterator();

        while (iterate.hasNext()) {
            ProcessModel next = iterate.next();

            if (next.getSizePage() <= this.task.getAvailibleMemory()) {
                next.setState(newState);
                iterate.set(next);
                this.task.setUsedMemory(next.getSizePage() + this.task.getUsedMemory());
                this.generateMemorySpace(next.getId(), next.getSizePage());
            }
        }
    }
}
