package ada.osc.taskie;

import java.util.List;

import ada.osc.taskie.model.Task;
import ada.osc.taskie.persistance.FakeDatabase;
import ada.osc.taskie.model.TaskGenerator;
import io.realm.Realm;
import io.realm.RealmResults;

public class TaskRepository {

	private static TaskRepository sRepository = null;
	private static final int INITIAL_TASK_COUNT = 10;

	private FakeDatabase mDatabase;

	private TaskRepository(){
		mDatabase = new FakeDatabase();
	}

	public static synchronized TaskRepository getInstance(){
		if(sRepository == null){
			sRepository = new TaskRepository();
		}
		return sRepository;
	}

	public List<Task> getTasks() {
		return mDatabase.getTasks();
	}
	public List<Task> getTasksAscending() {
		return mDatabase.getTasksAscending();
	}

	public List<Task> getTasksDescending() {
		return mDatabase.getTasksDescending();
	}

	public void saveTask(Task task) {
		mDatabase.save(task);
	}

	public void removeTask(Task task) {
		mDatabase.delete(task);
	}


}


