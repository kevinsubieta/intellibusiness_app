package intellisoft.bo.com.intellibusiness.entity.Principal;

import java.io.Serializable;

import intellisoft.bo.com.intellibusiness.entity.anotation.Ignore;
import intellisoft.bo.com.intellibusiness.entity.anotation.Key;

@SuppressWarnings("serial")
public class Entity implements Serializable, Cloneable {

	@Key
	protected int id;

	@Ignore
	private Action action = Action.None;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
