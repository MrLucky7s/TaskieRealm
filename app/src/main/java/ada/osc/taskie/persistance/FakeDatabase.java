package ada.osc.taskie.persistance;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ada.osc.taskie.model.Task;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class FakeDatabase {

	private Realm mRealm;

	public FakeDatabase(){
		mRealm = Realm.getDefaultInstance();
	}

	public List<Task> getTasks(){
		return mRealm.where(Task.class).findAll();
	}

	public List<Task> getTasksAscending() {
		return mRealm.where(Task.class).findAll().sort("mPriority", Sort.ASCENDING);
	}

	public List<Task> getTasksDescending() {
		return mRealm.where(Task.class).findAll().sort("mPriority", Sort.ASCENDING);
	}

	public void save(Task task){
		mRealm.beginTransaction();
		Task newTask = mRealm.createObject(Task.class, UUID.randomUUID().toString());
		newTask.setTitle(task.getTitle());
		newTask.setDescription(task.getDescription());
		newTask.setTaskPriorityEnum(task.getTaskPriorityEnum());
		mRealm.commitTransaction();
	}

	public void delete(final Task task) {
		mRealm.executeTransaction(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				RealmResults<Task> rows = realm.where(Task.class)
						.equalTo(Task.FIELD_ID,task.getId())
						.findAll();
				rows.deleteAllFromRealm();
			}
		});
	}
}
