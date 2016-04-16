package intellisoft.bo.com.intellibusiness.Entity.Principal;

import android.util.Log;

import java.io.Serializable;

import intellisoft.bo.com.intellibusiness.Entity.anotation.Ignore;
import intellisoft.bo.com.intellibusiness.Entity.anotation.Key;

@SuppressWarnings("serial")
public class Entity implements Serializable, Cloneable {

	@Key
	protected Long id;

	@Ignore
	private Action action = Action.None;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Action getAction() {
		return this.action;
	}

	public void setAction(Action value) {
		this.action = value;
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> T getClone() {

		Entity obj = null;
		try {
			obj = (Entity) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return (T) obj;
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> T getMe() {
		return (T) this;
	}
}
